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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /*
    새로운 멘토 등록
     */
    @PostMapping("/mentor/create")
    public ResponseEntity<MentorDetailResponse> CreateMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MentorDetailRequest mentorDetailRequest
    ) {
        return ResponseEntity.ok()
                .body(userService.CreateMentorDetail(mentorDetailRequest, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토 상세 조회
     */
    @GetMapping("/mentor/view")
    public ResponseEntity<MentorDetailResponse> ViewMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.ViewMentorDetail(userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토 상세 정보 수정
     */
    @PatchMapping("/mentor/update")
    public ResponseEntity<MentorDetailResponse> UpdateMentorDetail(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody MentorDetailRequest mentorDetailRequest
    ){
        return ResponseEntity.ok()
                .body(userService.UpdateMentorDetail(mentorDetailRequest, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘티 상세 조회
     */
    @GetMapping("/mentee/view")
    public ResponseEntity<MenteeDetailResponse> ViewMenteeDetail(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.ViewMenteeDetail(userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘티가 승인받은 멘토의 리스트 조회
    */
    @GetMapping("/mentee/mentorlist")
    public ResponseEntity<MentorSummariesResponse> ViewAcceptedMentorList(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.ViewAcceptedMentorList(userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘티가 멘토의 정보 조회
     */
    @GetMapping("/mentee/{mentorId}")
    public ResponseEntity<MentorForMenteeResponse> ViewMentorByMentee(
            @PathVariable("mentorId") Long mentorId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(userService.ViewMentorByMentee(mentorId));
    }
}
