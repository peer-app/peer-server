package com.example.peer.domain.consulting.exception;

import com.example.peer.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ConsultingErrorCode implements ErrorCode {
    CONSULTING_NOT_FOUND(HttpStatus.BAD_REQUEST, "상담을 찾을 수 없습니다."),
    CANNOT_REQUEST_CONSULTING_DURING_THIS_SCHEDULE(HttpStatus.BAD_REQUEST, "해당 일정에는 상담을 신청할 수 없습니다."),
    CANNOT_ACCESS_CONSULTING(HttpStatus.BAD_REQUEST, "해당 상담에 접근할 수 없습니다."),
    CANNOT_CONSULT_DURING_THIS_SCHEDULE(HttpStatus.BAD_REQUEST, "해당 일정에 상담을 수락할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
