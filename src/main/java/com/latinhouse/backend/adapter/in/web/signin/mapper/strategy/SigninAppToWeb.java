package com.latinhouse.backend.adapter.in.web.signin.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.dto.SigninAppResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SigninAppToWeb implements AppToWebStrategy<SigninAppResponse, SigninWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return SigninAppResponse.class.isAssignableFrom(a)
                && SigninWebResponse.class.isAssignableFrom(w);
    }

    @Override public SigninWebResponse toWebRes(SigninAppResponse appRes) {
        return SigninWebResponse.builder()
                .accessToken(appRes.getToken())
                .tokenType("Bearer")
                .issuedAt(Instant.now().toString())
                .build();
    }
}