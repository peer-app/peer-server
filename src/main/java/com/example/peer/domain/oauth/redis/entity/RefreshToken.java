package com.example.peer.domain.oauth.redis.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 86400)
@Builder
public class RefreshToken {
    @Id
    private String id;
    private String token;
}
