package com.latinhouse.backend.adapter.in.web.signup.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.dto.AddUserAppRequest;
import org.springframework.stereotype.Component;

@Component
public class SignupWebToApp implements WebToAppStrategy<SignupWebRequest, AddUserAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
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
