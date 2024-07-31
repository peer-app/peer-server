package com.example.peer.domain.user.controller;

import com.example.peer.domain.user.dto.request.MentorDetailRequest;
import com.example.peer.domain.user.dto.response.MenteeDetailResponse;
import com.example.peer.domain.user.dto.response.MentorDetailResponse;
import com.example.peer.domain.user.dto.response.MentorForMenteeResponse;
import com.example.peer.domain.user.dto.response.MentorSummariesResponse;
import com.example.peer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    새로운 멘토 등록
     */
    @PostMapping("/mentor/create")
    public ResponseEntity<MentorDetailResponse> createMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MentorDetailRequest mentorDetailRequest
    ) {
        return ResponseEntity.ok()
                .body(userService.createMentorDetail(mentorDetailRequest, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토 상세 조회
     */
    @GetMapping("/mentor/view")
    public ResponseEntity<MentorDetailResponse> viewMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.viewMentorDetail(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토 상세 정보 수정
     */
    @PatchMapping("/mentor/update")
    public ResponseEntity<MentorDetailResponse> updateMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MentorDetailRequest mentorDetailRequest
    ){
        return ResponseEntity.ok()
                .body(userService.updateMentorDetail(mentorDetailRequest, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티 상세 조회
     */
    @GetMapping("/mentee/view")
    public ResponseEntity<MenteeDetailResponse> viewMenteeDetail(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.viewMenteeDetail(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티가 승인받은 멘토의 리스트 조회
    */
    @GetMapping("/mentee/mentorlist")
    public ResponseEntity<MentorSummariesResponse> viewAcceptedMentorList(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.viewAcceptedMentorList(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티가 멘토의 정보 조회
     */
    @GetMapping("/mentee/{mentorId}")
    public ResponseEntity<MentorForMenteeResponse> viewMentorByMentee(
            @PathVariable("mentorId") Long mentorId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.viewMentorByMentee(mentorId));
    }

    @PostMapping("/api/mentor/signup")
    public ResponseEntity<Void> signUpMentor(
            @RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        userService.signUpMentor((String) payload.get("phoneNumber"), userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/mentee/signup")
    public ResponseEntity<Void> signUpMentee(
            @RequestBody Map<String, Object> payload,
            @AuthenticationPrincipal UserDetails userDetails
    ){
        userService.signUpMentee((String) payload.get("phoneNumber"), userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}
