package com.latinhouse.backend.application.signup.mapper.strategy;

import com.latinhouse.backend.application.signup.mapper.AppToCommandStrategy;
import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import com.latinhouse.backend.port.in.dto.AddUserAppRequest;
import org.springframework.stereotype.Component;

@Component
public class AddAppToCommand implements AppToCommandStrategy<AddUserAppRequest, AddUserCommand> {

    @Override
    public boolean supports(Class<?> a, Class<?> c) {
        return AddUserAppRequest.class.isAssignableFrom(a)
                && AddUserCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddUserCommand toCommand(AddUserAppRequest appReq) {
        return AddUserCommand.builder()
                .email(appReq.getEmail())
                .password(appReq.getPassword())
                .build();
    }
}
