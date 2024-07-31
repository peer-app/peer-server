package com.example.peer.domain.oauth.controller;

import com.example.peer.domain.oauth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @PostMapping("/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(oAuth2Service.handleKakaoCallback(body.get("code")));
    }
}
