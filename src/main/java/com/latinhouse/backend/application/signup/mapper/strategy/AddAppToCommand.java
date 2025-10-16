package com.latinhouse.backend.application.signup.mapper.strategy;

import com.latinhouse.backend.application.signup.mapper.AppToCommandStrategy;
import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import com.latinhouse.backend.port.in.dto.AddUserAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddAppToCommand implements AppToCommandStrategy<AddUserAppRequest, AddUserCommand> {

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
