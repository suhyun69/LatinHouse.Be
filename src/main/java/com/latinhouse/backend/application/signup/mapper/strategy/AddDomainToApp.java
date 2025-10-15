package com.latinhouse.backend.application.signup.mapper.strategy;

import com.latinhouse.backend.application.signup.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.dto.AddUserAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddDomainToApp implements DomainToAppStrategy<User, AddUserAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
        return User.class.isAssignableFrom(d)
                && AddUserAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public AddUserAppResponse toAppRes(User user) {
        return AddUserAppResponse.builder()
                .email(user.getEmail())
                .build();
    }
}