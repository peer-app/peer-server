package com.example.peer.domain.oauth.service;

import com.example.peer.domain.oauth.exception.AuthErrorCode;
import com.example.peer.domain.oauth.exception.AuthException;
import com.example.peer.domain.user.entity.User;
import com.example.peer.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findBySocialId(username)
                .orElseThrow(() -> new AuthException(AuthErrorCode.CANNOT_FOUND_USER));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getSocialId())
                .password("") // No password required for JWT authentication
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
