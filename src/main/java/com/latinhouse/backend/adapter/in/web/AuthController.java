package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.config.JwtTokenProvider;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;

    public AuthController(AuthenticationManager authManager, JwtTokenProvider jwt) {
        this.authManager = authManager;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        // call CustomUserDetailsService
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );
        User principal = (User) authentication.getPrincipal();

        String token = jwt.createAccessToken(
                principal.getUsername(),
                Map.of("roles", principal.getAuthorities()) // 필요 시 커스텀 클레임
        );

        return ResponseEntity.ok(Map.of(
                "accessToken", token,
                "tokenType", "Bearer",
                "issuedAt", Instant.now().toString()
        ));
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
}