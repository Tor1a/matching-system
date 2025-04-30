package com.matching.matchingsystem.domain.auth.controller;

import com.matching.matchingsystem.domain.auth.dto.AuthResponse;
import com.matching.matchingsystem.domain.auth.dto.LoginRequest;
import com.matching.matchingsystem.domain.auth.dto.SignupRequest;
import com.matching.matchingsystem.domain.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest.getUsername(), signupRequest.getPassword());
        System.out.println("signup request sent");
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
