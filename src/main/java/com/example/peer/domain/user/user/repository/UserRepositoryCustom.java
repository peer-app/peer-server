package com.example.peer.domain.user.user.repository;

import com.example.peer.domain.user.user.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findMentorByIsAccepted();
}
