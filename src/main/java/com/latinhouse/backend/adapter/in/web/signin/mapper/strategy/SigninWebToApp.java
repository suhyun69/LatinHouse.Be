package com.latinhouse.backend.adapter.in.web.signin.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebRequest;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.signin.SigninAppRequest;
import org.springframework.stereotype.Component;

@Component
public class SigninWebToApp implements WebToAppStrategy<SigninWebRequest, SigninAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
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
