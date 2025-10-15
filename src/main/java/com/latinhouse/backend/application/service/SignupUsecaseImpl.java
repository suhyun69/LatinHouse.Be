package com.latinhouse.backend.application.service;

import com.latinhouse.backend.port.in.AddUserAppRequest;
import com.latinhouse.backend.port.in.AddUserAppResponse;
import com.latinhouse.backend.port.in.SignupUseCase;
import com.latinhouse.backend.application.domain.user.AddUserCommand;
import com.latinhouse.backend.application.domain.user.User;
import com.latinhouse.backend.application.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUsecaseImpl implements SignupUseCase {

    private final UserService userService;

    @Override
    @Transactional
    public AddUserAppResponse addByEmail(AddUserAppRequest appReq) {
        AddUserCommand req = AddUserCommand.from(appReq);
        User user = userService.addUser(req);

        return new AddUserAppResponse(user);
    }
}
