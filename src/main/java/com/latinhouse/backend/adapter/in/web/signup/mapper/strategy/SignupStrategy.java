package com.latinhouse.backend.adapter.in.web.signup.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppResponse;
import org.springframework.stereotype.Component;

@Component
public class SignupStrategy implements
        AppToWebStrategy<AddUserAppResponse, SignupWebResponse>,
        WebToAppStrategy<SignupWebRequest, AddUserAppRequest> {

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return AddUserAppResponse.class.isAssignableFrom(a)
                && SignupWebResponse.class.isAssignableFrom(w);
    }

    @Override public SignupWebResponse toWebRes(AddUserAppResponse appRes) {
        return SignupWebResponse.builder()
                .email(appRes.getEmail())
                .build();
    }

    @Override public boolean webToAppSupports(Class<?> w, Class<?> a) {
        return SignupWebRequest.class.isAssignableFrom(w)
                && AddUserAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddUserAppRequest toAppReq(SignupWebRequest webReq) {
        return AddUserAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();
    }
}