package com.example.peer.domain.user.user.repository;

import java.util.Optional;

import com.example.peer.domain.user.user.entity.OauthType;
import com.example.peer.domain.user.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
	Optional<User> findByEmail (String email);
	Optional<User> findBySocialIdAndOauthType(String SocialId, OauthType oauthType);
	Optional<User> findById(Long id);
	Optional<User> findByName(String Name);
}
