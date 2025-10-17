package com.latinhouse.backend.adapter.in.web.signup.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.addUser.AddUserAppResponse;
import org.springframework.stereotype.Component;

@Component
public class SignupAppToWeb implements AppToWebStrategy<AddUserAppResponse, SignupWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return AddUserAppResponse.class.isAssignableFrom(a)
                && SignupWebResponse.class.isAssignableFrom(w);
    }

    @Override public SignupWebResponse toWebRes(AddUserAppResponse appRes) {
        return SignupWebResponse.builder()
                .email(appRes.getEmail())
                .build();
    }
}