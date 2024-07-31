package com.example.peer.domain.user.service;

import com.example.peer.domain.user.dto.request.MentorDetailRequest;
import com.example.peer.domain.user.dto.response.*;
import com.example.peer.domain.user.entity.MentorDetail;
import com.example.peer.domain.user.exception.UserErrorCode;
import com.example.peer.domain.user.exception.UserException;
import com.example.peer.domain.user.entity.Role;
import com.example.peer.domain.user.entity.User;
import com.example.peer.domain.user.repository.MentorDetailRepository;
import com.example.peer.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final MentorDetailRepository mentorDetailRepository;

    public Long getIdBySocailId(String socialId) {
        return userRepository.findBySocialId(socialId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        ).getId();
    }

    @Transactional
    public void signUpMentor(String phoneNumber, String socialId) {
        User user = userRepository.findBySocialId(socialId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        user.updatePhoneNumber(phoneNumber);
    }

    @Transactional
    public void signUpMentee(String phoneNumber, String socialId) {
        User user = userRepository.findBySocialId(socialId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        user.updateRole(Role.MENTEE);
        user.updatePhoneNumber(phoneNumber);
    }

    /*
    멘토-새로운 멘토 등록
    oauth를 통해 user에는 이미 저장 가정
     */
    @Transactional
    public MentorDetailResponse createMentorDetail(MentorDetailRequest mentorDetailRequest, Long mentorId) {
        MentorDetail mentorDetail = mentorDetailRepository.save(MentorDetail.builder()
                .nickname(mentorDetailRequest.getNickname())
                .position(mentorDetailRequest.getPosition())
                .introduction(mentorDetailRequest.getIntroduction())
                .openTalkLink(mentorDetailRequest.getOpenTalkLink())
                .keywords(mentorDetailRequest.getKeywords())
                .build());
        User mentor = userRepository.findById(mentorId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        mentor.updateRole(Role.MENTOR);
        mentor.updatePhoneNumber(mentorDetailRequest.getPhoneNumber());
        mentor.updateMentorDetail(mentorDetail);
        return MentorDetailResponse.builder()
                .mentorDetail(mentorDetail)
                .mentor(mentor)
                .build();
    }

    /*
    멘토-승인과정 -> 추후에 관리자 페이지 도입시 추가
     */

    /*
    멘토-멘토 상세 조회
     */
    public MentorDetailResponse viewMentorDetail(Long mentorId) {
        User mentor = userRepository.findById(mentorId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        return MentorDetailResponse.builder()
                .mentorDetail(mentor.getMentorDetail())
                .mentor(mentor)
                .build();
    }

    /*
    멘토-멘토 상세 수정
     */
    @Transactional
    public MentorDetailResponse updateMentorDetail(MentorDetailRequest mentorDetailRequest, Long mentorId) {
        User mentor = userRepository.findById(mentorId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        mentor.updatePhoneNumber(mentorDetailRequest.getPhoneNumber());
        mentor.getMentorDetail().updateMentorDetail(mentorDetailRequest.getNickname(), mentorDetailRequest.getPosition(), mentorDetailRequest.getIntroduction(),
                mentorDetailRequest.getOpenTalkLink(), mentorDetailRequest.getKeywords());
        return MentorDetailResponse.builder()
                .mentorDetail(mentor.getMentorDetail())
                .mentor(mentor)
                .build();
    }

    /*
    멘티-멘티 상세 조회
     */
    public MenteeDetailResponse viewMenteeDetail(Long menteeId) {
        return MenteeDetailResponse.builder()
                .mentee(userRepository.findById(menteeId).orElseThrow(
                        () -> new UserException(UserErrorCode.USER_NOT_FOUND)
                ))
                .build();
    }

    /*
    멘티-승인된 멘토 리스트 조회
    */
    public MentorSummariesResponse viewAcceptedMentorList(Long menteeId) {
        MentorSummariesResponse mentorSummariesResponse = MentorSummariesResponse.builder().build();
        for (User mentor : userRepository.findMentorByIsAccepted()) {
            mentorSummariesResponse.UpdateMentorSummary(MentorSummary.builder()
                    .mentor(mentor)
                    .mentorDetail(mentor.getMentorDetail())
                    .build());
        }
        return mentorSummariesResponse;
    }

    /*
    멘티-멘토 상세 조회
     */
    public MentorForMenteeResponse viewMentorByMentee(Long mentorId) {
        User mentor = userRepository.findById(mentorId).orElseThrow(
                () -> new UserException(UserErrorCode.USER_NOT_FOUND)
        );
        return MentorForMenteeResponse.builder()
                .mentor(mentor)
                .mentorDetail(mentor.getMentorDetail())
                .build();
    }
}
