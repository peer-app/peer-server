package com.example.peer.domain.security.security.exception;

import com.example.peer.common.exception.BusinessException;

import lombok.Getter;

@Getter
public class SecurityException extends BusinessException {
	SecurityErrorCode securityErrorCode;

	public SecurityException(SecurityErrorCode securityErrorCode) {
		super(securityErrorCode);
	}
}