package com.example.peer.domain.consulting.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ConsultingSummariesResponse {

    private List<ConsultingSummary> consultingSummaries;

    @Builder
    public ConsultingSummariesResponse() {
        this.consultingSummaries = new ArrayList<>();
    }

    public void updateConsultingSummary(ConsultingSummary consultingSummary) {
        this.consultingSummaries.add(consultingSummary);
    }
}
