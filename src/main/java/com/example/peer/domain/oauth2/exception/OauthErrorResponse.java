package com.example.peer.domain.oauth2.exception;


import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OauthErrorResponse {
	private int status;
	private String code;
	private String message;

	public static ResponseEntity<OauthErrorResponse> toResponseEntity(OauthErrorCode e) {
		return ResponseEntity
			.status(e.getHttpStatus())
			.body(OauthErrorResponse.builder()
				.status(e.getHttpStatus().value())
				.code(e.name())
				.message(e.getMessage())
				.build()
			);
	}
}