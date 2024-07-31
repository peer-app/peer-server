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
    public ResponseEntity<ConsultingDetailResponse> createConsulting(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ConsultingRequest consultingRequest
    ) {
        return ResponseEntity.ok()
                .body(consultingService.createConsulting(consultingRequest, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 상담 상세를 조회
     */
    @GetMapping("/mentor/{consultingId}")
    public ResponseEntity<ConsultingDetailResponse> viewConsultingDetailMentor(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewConsultingDetailMentor(consultingId, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티가 상담 상세를 조회
     */
    @GetMapping("/mentee/{consultingId}")
    public ResponseEntity<ConsultingDetailResponse> viewConsultingDetailMentee(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewConsultingDetailMentee(consultingId, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 세로운 상담을 수락
     */
    @GetMapping("/mentor/{consultingId}/accept")
    public ResponseEntity<ConsultingDetailResponse> acceptConsulting(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.acceptConsulting(consultingId, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 세로운 상담을 거절
     */
    @GetMapping("/mentor/{consultingId}/reject")
    public ResponseEntity<ConsultingDetailResponse> rejectConsulting(
            @PathVariable("consultingId") Long consultingId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.rejectConsulting(consultingId, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 자신의 과거 상담 리스트 조회
     */
    @GetMapping("/mentor/past")
    public ResponseEntity<ConsultingSummariesResponse> viewPastConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPastConsultingMentor(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티가 자신의 과거 상담 리스트 조회
     */
    @GetMapping("/mentee/past")
    public ResponseEntity<ConsultingSummariesResponse> viewPastConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPastConsultingMentee(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 자신의 수락된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/accepted")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentAcceptedConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentor(userService.getIdBySocailId(userDetails.getUsername()), State.ACCEPTED));
    }

    /*
    멘토가 자신의 대기중 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/waiting")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentWaitingConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentor(userService.getIdBySocailId(userDetails.getUsername()), State.WAITING));
    }

    /*
    멘토가 자신의 거절된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentor/rejected")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentRejectedConsultingMentor(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentor(userService.getIdBySocailId(userDetails.getUsername()), State.REJECTED));
    }

    /*
    멘티가 자신의 수락된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/accepted")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentAcceptedConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentee(userService.getIdBySocailId(userDetails.getUsername()), State.ACCEPTED));
    }

    /*
    멘티가 자신의 대기중 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/waiting")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentWaitingConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentee(userService.getIdBySocailId(userDetails.getUsername()), State.WAITING));
    }

    /*
    멘티가 자신의 거절된 진행 예정 상담 리스트 조회
     */
    @GetMapping("/mentee/rejected")
    public ResponseEntity<ConsultingSummariesResponse> viewPresentRejectedConsultingMentee(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(consultingService.viewPresentConsultingMentee(userService.getIdBySocailId(userDetails.getUsername()), State.REJECTED));
    }
}
