package com.example.peer.domain.user.user.dto.response;

import com.example.peer.domain.user.user.entity.Keyword;
import com.example.peer.domain.user.user.entity.MentorDetail;
import com.example.peer.domain.user.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorDetailResponse {

    private Long mentorId;

    private String name;

    private String email;

    private String phoneNumber;

    private String nickname;

    private String position;

    private String introduction;

    private Boolean isAccepted;

    private String openTalkLink;

    private List<Keyword> keywords;

    @Builder
    public MentorDetailResponse(User mentor, MentorDetail mentorDetail) {
        this.mentorId = mentor.getId();
        this.name = mentor.getName();
        this.email = mentor.getEmail();
        this.phoneNumber = mentor.getPhoneNumber();
        this.nickname = mentorDetail.getNickname();
        this.position = mentorDetail.getPosition();
        this.introduction = mentorDetail.getIntroduction();
        this.isAccepted = mentorDetail.getIsAccepted();
        this.openTalkLink = mentorDetail.getOpenTalkLink();
        this.keywords = mentorDetail.getKeywords();
    }
}
