package com.latinhouse.backend.application.signup.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import com.latinhouse.backend.port.in.signup.AddUserAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupAppToCommand implements AppToCommandStrategy<AddUserAppRequest, AddUserCommand> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> a, Class<?> c) {
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
}
