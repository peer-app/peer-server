package com.example.peer.domain.consulting.dto.response;

import com.example.peer.domain.consulting.entity.Consulting;
import com.example.peer.domain.consulting.entity.ConsultingDetail;
import com.example.peer.domain.consulting.entity.State;
import com.example.peer.domain.consulting.entity.TeamComposition;
import com.example.peer.domain.user.entity.MentorDetail;
import com.example.peer.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConsultingDetailResponse {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime consultingDateTime;

    private String message;

    private String menteePosition;

    private int managerCount;

    private int designerCount;

    private int frontendCount;

    private int backendCount;

    private State state;

    private String menteeName;

    private String mentorNickname;

    private String mentorPosition;

    private String openTalkLink;

    @Builder
    public ConsultingDetailResponse(Consulting consulting, ConsultingDetail consultingDetail, TeamComposition teamComposition, MentorDetail mentorDetail, User mentee) {
        this.id = consulting.getId();
        this.consultingDateTime = consulting.getConsultingDateTime();
        this.message = consultingDetail.getMessage();
        this.menteePosition = consultingDetail.getMenteePosition();
        this.managerCount = teamComposition.getManagerCount();
        this.designerCount = teamComposition.getDesignerCount();
        this.frontendCount = teamComposition.getFrontendCount();
        this.backendCount = teamComposition.getBackendCount();
        this.state = consulting.getState();
        this.menteeName = mentee.getName();
        this.mentorNickname = mentorDetail.getNickname();
        this.mentorPosition = mentorDetail.getPosition();

        if (consulting.getState()==State.ACCEPTED) {
            this.openTalkLink = mentorDetail.getOpenTalkLink();
        } else {
            this.openTalkLink = "NONE";
        }
    }
}
