package com.example.peer.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	MENTOR("ROLE_MENTOR"),
	MENTEE("ROLE_MENTEE"),
	ADMIN("ROLE_ADMIN"),
	GUEST("ROLE_GUEST");

	private final String key;
}
