package com.example.peer.domain.oauth.exception;

import com.example.peer.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    INVALID_OAUTH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 OAuth 토큰입니다."),
    INVALID_OAUTH_CODE(HttpStatus.UNAUTHORIZED, "유효하지 않은 OAuth 코드입니다."),
    CANNOT_FOUND_USER(HttpStatus.UNAUTHORIZED, "토큰 정보와 일치하는 유저가 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
