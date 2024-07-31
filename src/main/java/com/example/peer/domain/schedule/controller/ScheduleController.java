package com.example.peer.domain.schedule.controller;

import com.example.peer.domain.schedule.dto.request.ScheduleRuleRequest;
import com.example.peer.domain.schedule.dto.response.PossibleSchedulesResponse;
import com.example.peer.domain.schedule.dto.response.ScheduleRuleResponse;
import com.example.peer.domain.schedule.service.ScheduleService;
import com.example.peer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;

    /*
    멘토가 일정 규칙을 생성
     */
    @PostMapping("/mentor/create")
    public ResponseEntity<ScheduleRuleResponse> CreateScheduleRule(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ScheduleRuleRequest scheduleRuleRequest
    ) {
        return ResponseEntity.ok()
                .body(scheduleService.CreateScheduleRule(scheduleRuleRequest, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 일정 규칙을 수정
     */
    @PatchMapping("/mentor/update")
    public ResponseEntity<ScheduleRuleResponse> UpdateScheduleRule(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ScheduleRuleRequest scheduleRuleRequest
    ) {
        return ResponseEntity.ok()
                .body(scheduleService.UpdateScheduleRule(scheduleRuleRequest, userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘토가 자신의 일정 규칙을 조회
     */
    @GetMapping("/mentor/view")
    public ResponseEntity<ScheduleRuleResponse> ViewMyScheduleRule(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(scheduleService.ViewScheduleRule(userService.getIdBySocailId(userDetails.getUsername())));
    }

    /*
    멘티가 멘토의 상담 가능 일정을 조회
     */
    @GetMapping("/mentee/view/{mentorId}")
    public ResponseEntity<PossibleSchedulesResponse> ViewPossibleSchedules(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("mentorId") Long mentorId
    ) {
        return ResponseEntity.ok()
                .body(scheduleService.ViewPossibleSchedules(mentorId));
    }
}
