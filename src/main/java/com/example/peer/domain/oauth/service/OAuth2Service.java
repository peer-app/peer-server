package com.example.peer.domain.oauth.service;

import com.example.peer.domain.oauth.exception.AuthErrorCode;
import com.example.peer.domain.oauth.exception.AuthException;
import com.example.peer.domain.oauth.jwt.JwtUtil;
import com.example.peer.domain.oauth.redis.entity.RefreshToken;
import com.example.peer.domain.oauth.redis.repository.RefreshTokenRepository;
import com.example.peer.domain.user.entity.Role;
import com.example.peer.domain.user.entity.User;
import com.example.peer.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class OAuth2Service {
    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.token-uri}")
    private String tokenUrl;

    @Value("${kakao.user-info-uri}")
    private String userInfoUrl;

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtUtil jwtUtil;

    public Map<String, String> handleKakaoCallback(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            String accessToken = (String) response.getBody().get("access_token");

            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.setBearerAuth(accessToken);

            HttpEntity<String> userInfoRequest = new HttpEntity<>(userInfoHeaders);
            ResponseEntity<Map> userInfoResponse = restTemplate.exchange(userInfoUrl, HttpMethod.GET, userInfoRequest, Map.class);

            if (userInfoResponse.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> userData = (Map<String, Object>) userInfoResponse.getBody().get("kakao_account");
                String id = String.valueOf(userInfoResponse.getBody().get("id"));
                String email = (String) userData.get("email");
                String nickname = (String) ((Map<String, Object>) userData.get("profile")).get("nickname");
                String profileImageUrl = (String) ((Map<String, Object>) userData.get("profile")).get("profile_image_url");

                User user = userRepository.save(userRepository.findBySocialId(id)
                        .orElse(User.builder()
                                .socialId(id)
                                .role(Role.GUEST)
                                .email(email)
                                .name(nickname)
                                .profileImageUrl(profileImageUrl)
                                .build()));

                String jwtToken = jwtUtil.generateToken(id);
                String refreshToken = jwtUtil.generateRefreshToken(id);

                refreshTokenRepository.save(RefreshToken.builder()
                                .id(id)
                                .token(refreshToken)
                        .build());

                return Map.of("accessToken", jwtToken, "refreshToken", refreshToken, "role", user.getRole().toString().toLowerCase());
            } else {
                throw new AuthException(AuthErrorCode.INVALID_OAUTH_TOKEN);
            }
        } else {
            throw new AuthException(AuthErrorCode.INVALID_OAUTH_CODE);
        }
    }
}
