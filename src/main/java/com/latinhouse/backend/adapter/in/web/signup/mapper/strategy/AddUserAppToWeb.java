package com.latinhouse.backend.adapter.in.web.signup.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signup.dto.AddUserWebResponse;
import com.latinhouse.backend.adapter.in.web.signup.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.dto.AddUserAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddUserAppToWeb implements AppToWebStrategy<AddUserAppResponse, AddUserWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return AddUserAppResponse.class.isAssignableFrom(a)
                && AddUserWebResponse.class.isAssignableFrom(w);
    }

    @Override public AddUserWebResponse toWebRes(AddUserAppResponse appRes) {
        return AddUserWebResponse.builder()
                .email(appRes.getEmail())
                .build();
    }
}