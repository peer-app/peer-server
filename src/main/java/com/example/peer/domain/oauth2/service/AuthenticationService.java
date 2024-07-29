package com.example.peer.domain.oauth2.service;

import com.example.peer.domain.oauth2.exception.AuthException;
import com.example.peer.domain.user.entity.User;
import com.example.peer.domain.user.exception.UserException;
import com.example.peer.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.peer.domain.oauth2.exception.AuthErrorCode.NO_ACCESS;
import static com.example.peer.domain.user.exception.UserErrorCode.USER_NOT_FOUND;


@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public User getMemberOrThrow(String memberKey) {
        return userRepository.findByMemberKey(memberKey)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
    }

    public void checkAccess(String memberKey, User user) {
        if (!user.getMemberKey().equals(memberKey)) {
            throw new AuthException(NO_ACCESS);
        }
    }
}
