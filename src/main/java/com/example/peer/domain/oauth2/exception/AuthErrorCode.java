package com.example.peer.domain.oauth2.exception;

import com.example.peer.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    NO_ACCESS(HttpStatus.UNAUTHORIZED, "접근 권한이 없습니다."),
    ILLEGAL_REGISTRATION_ID(HttpStatus.UNAUTHORIZED, "잘못된 등록 ID입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_JWT_SIGNATURE(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT 사인입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
