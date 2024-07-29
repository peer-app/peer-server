package com.example.peer.domain.security.service;

import com.example.peer.domain.security.utils.JwtTokenProvider;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenService {
	private final JwtTokenProvider jwtTokenProvider;

	public Long getUserIdFromToken(String accessToken) {
		Claims claims = jwtTokenProvider.parseClaims(accessToken);
		return Long.valueOf(claims.getSubject());
	}
}
