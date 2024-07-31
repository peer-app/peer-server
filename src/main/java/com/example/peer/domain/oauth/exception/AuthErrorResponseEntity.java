package com.example.peer.domain.oauth.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class AuthErrorResponseEntity {

    private int status;
    private String code;
    private String message;

    public static ResponseEntity<AuthErrorResponseEntity> toResponseEntity(AuthErrorCode e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(AuthErrorResponseEntity.builder()
                        .status(e.getHttpStatus().value())
                        .code(e.name())
                        .message(e.getMessage())
                        .build()
                );
    }
}
