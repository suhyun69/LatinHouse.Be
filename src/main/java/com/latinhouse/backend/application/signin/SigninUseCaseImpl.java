package com.latinhouse.backend.application.signin;

import com.latinhouse.backend.config.jwt.JwtTokenProvider;
import com.latinhouse.backend.domain.user.CustomUserDetails;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.service.CustomUserDetailsService;
import com.latinhouse.backend.domain.user.service.UserService;
import com.latinhouse.backend.port.in.SigninUseCase;
import com.latinhouse.backend.port.in.dto.SigninAppRequest;
import com.latinhouse.backend.port.in.dto.SigninAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SigninUseCaseImpl implements SigninUseCase {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;

    @Override
    public SigninAppResponse signin(SigninAppRequest appReq) {

        // call CustomUserDetailsService
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(appReq.getEmail(), appReq.getPassword())
        );
        // User principal = (User) authentication.getPrincipal();
        CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal(); // ⬅ 변경
        User user = cud.getUser();

        String token = jwt.createAccessToken(
                user.getEmail(),
                Map.of("roles", user.getRole()) // 필요 시 커스텀 클레임
        );

        return SigninAppResponse.builder()
                .token(token)
                .build();
    }
}
