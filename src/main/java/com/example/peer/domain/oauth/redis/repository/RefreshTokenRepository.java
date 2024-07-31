package com.example.peer.domain.oauth.redis.repository;

import com.example.peer.domain.oauth.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
