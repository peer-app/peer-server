package com.example.peer.domain.consulting.controller;

import com.example.peer.domain.consulting.dto.request.ConsultingRequest;
import com.example.peer.domain.consulting.dto.response.ConsultingDetailResponse;
import com.example.peer.domain.consulting.dto.response.ConsultingSummariesResponse;
import com.example.peer.domain.consulting.entity.State;
import com.example.peer.domain.consulting.service.ConsultingService;
import com.example.peer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/consultings")
public class ConsultingController {

    private final ConsultingService consultingService;
    private final UserService userService;

    /*
    멘티가 새로운 상담을 신청
     */
    @PostMapping("/mentee/create")
    public ResponseEntity<ConsultingDetailResponse> CreateConsulting(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ConsultingRequest consultingRequest
    ) {
        return ResponseEntity.ok()
                .body(consultingService.CreateConsulting(consultingRequest, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토가 상담 상세를 조회
     */
    @GetMapping("/mentor/{consultingId}")
    public ResponseEntity<ConsultingDetailResponse> ViewConsultingDetailMentor(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewConsultingDetailMentor(consultingId, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘티가 상담 상세를 조회
     */
    @GetMapping("/mentee/{consultingId}")
    public ResponseEntity<ConsultingDetailResponse> ViewConsultingDetailMentee(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewConsultingDetailMentee(consultingId, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토가 세로운 상담을 수락
     */
    @GetMapping("/mentor/{consultingId}/accept")
    public ResponseEntity<ConsultingDetailResponse> AcceptConsulting(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.AcceptConsulting(consultingId, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토가 세로운 상담을 거절
     */
    @GetMapping("/mentor/{consultingId}/reject")
    public ResponseEntity<ConsultingDetailResponse> RejectConsulting(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.RejectConsulting(consultingId, userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토가 자신의 과거 상담 리스트 조회
     */
    @GetMapping("/mentor/past")
    public ResponseEntity<ConsultingSummariesResponse> ViewPastConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPastConsultingMentor(userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘티가 자신의 과거 상담 리스트 조회
     */
    @GetMapping("/mentee/past")
    public ResponseEntity<ConsultingSummariesResponse> ViewPastConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPastConsultingMentee(userService.findUserIdByMemberKey(userDetails.getUsername())));
    }

    /*
    멘토가 자신의 수락된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/accepted")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentAcceptedConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentor(userService.findUserIdByMemberKey(userDetails.getUsername()), State.ACCEPTED));
    }

    /*
    멘토가 자신의 대기중 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/waiting")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentWaitingConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentor(userService.findUserIdByMemberKey(userDetails.getUsername()), State.WAITING));
    }

    /*
    멘토가 자신의 거절된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/rejected")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentRejectedConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentor(userService.findUserIdByMemberKey(userDetails.getUsername()), State.REJECTED));
    }

    /*
    멘티가 자신의 수락된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/accepted")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentAcceptedConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentee(userService.findUserIdByMemberKey(userDetails.getUsername()), State.ACCEPTED));
    }

    /*
    멘티가 자신의 대기중 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/waiting")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentWaitingConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentee(userService.findUserIdByMemberKey(userDetails.getUsername()), State.WAITING));
    }

    /*
    멘티가 자신의 거절된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/rejected")
    public ResponseEntity<ConsultingSummariesResponse> ViewPresentRejectedConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.ViewPresentConsultingMentee(userService.findUserIdByMemberKey(userDetails.getUsername()), State.REJECTED));
    }
}
