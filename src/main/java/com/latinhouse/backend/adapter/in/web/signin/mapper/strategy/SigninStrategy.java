package com.latinhouse.backend.adapter.in.web.signin.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebRequest;
import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.signin.dto.SigninAppRequest;
import com.latinhouse.backend.port.in.signin.dto.SigninAppResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SigninStrategy implements
        AppToWebStrategy<SigninAppResponse, SigninWebResponse>,
        WebToAppStrategy<SigninWebRequest, SigninAppRequest> {

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
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

    @Override public boolean webToAppSupports(Class<?> w, Class<?> a) {
        return SigninWebRequest.class.isAssignableFrom(w)
                && SigninAppRequest.class.isAssignableFrom(a);
    }

    @Override public SigninAppRequest toAppReq(SigninWebRequest webReq) {
        return SigninAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();
    }
}