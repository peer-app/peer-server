package com.example.peer.domain.oauth.exception;

import com.example.peer.common.exception.BusinessException;
import lombok.Getter;

@Getter
public class AuthException extends BusinessException {
    AuthErrorCode authErrorCode;
    public AuthException(AuthErrorCode authErrorCode) {
        super(authErrorCode);
    }
}
