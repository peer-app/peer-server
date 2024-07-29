package com.example.peer.domain.oauth2.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse(@NotBlank String accessToken) {
}
