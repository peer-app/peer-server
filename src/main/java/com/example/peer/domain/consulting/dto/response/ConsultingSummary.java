package com.example.peer.domain.consulting.dto.response;

import com.example.peer.domain.consulting.entity.Consulting;
import com.example.peer.domain.consulting.entity.State;
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
public class ConsultingSummary {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime consultingDateTime;

    private String menteeName;

    private String mentorNickname;

    private State state;

    @Builder
    public ConsultingSummary(Consulting consulting, MentorDetail mentorDetail, User mentee) {
        this.id = consulting.getId();
        this.consultingDateTime = consulting.getConsultingDateTime();
        this.menteeName = mentee.getName();
        this.mentorNickname = mentorDetail.getNickname();
        this.state = consulting.getState();
    }
}
