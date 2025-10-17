package com.latinhouse.backend.application.signup.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupStrategy implements
        AppToCommandStrategy<AddUserAppRequest, AddUserCommand>,
        DomainToAppStrategy<User, AddUserAppResponse> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean appToCommandSupports(Class<?> a, Class<?> c) {
        return AddUserAppRequest.class.isAssignableFrom(a)
                && AddUserCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddUserCommand toCommand(AddUserAppRequest appReq) {
        return AddUserCommand.builder()
                .email(appReq.getEmail())
                .password(passwordEncoder.encode(appReq.getPassword()))
                .build();
    }

    @Override
    public boolean domainToAppSupports(Class<?> d, Class<?> a) {
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
