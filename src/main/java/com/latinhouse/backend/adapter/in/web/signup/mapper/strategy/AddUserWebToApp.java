package com.latinhouse.backend.adapter.in.web.signup.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.signup.dto.AddUserWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.AddUserAppRequest;
import org.springframework.stereotype.Component;

@Component
public class AddUserWebToApp implements WebToAppStrategy<AddUserWebRequest, AddUserAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
        return AddUserWebRequest.class.isAssignableFrom(w)
                && AddUserAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddUserAppRequest toAppReq(AddUserWebRequest webReq) {
        return AddUserAppRequest.builder()
            .email(webReq.getEmail())
            .password(webReq.getPassword())
            .build();
    }
}
