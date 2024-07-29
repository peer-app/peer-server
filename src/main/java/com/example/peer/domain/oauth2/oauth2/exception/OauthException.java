package com.example.peer.domain.oauth2.oauth2.exception;

import com.example.peer.common.exception.BusinessException;

import lombok.Getter;

@Getter
public class OauthException extends BusinessException {
	OauthErrorCode oauthErrorCode;

	public OauthException(OauthErrorCode oauthErrorCode) {
		super(oauthErrorCode);
	}
}