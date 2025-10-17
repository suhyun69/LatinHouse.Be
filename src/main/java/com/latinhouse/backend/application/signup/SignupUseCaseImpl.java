package com.latinhouse.backend.application.signup;

import com.latinhouse.backend.application.signup.mapper.SignupAppMapper;
import com.latinhouse.backend.port.in.addUser.AddUserAppRequest;
import com.latinhouse.backend.port.in.addUser.AddUserAppResponse;
import com.latinhouse.backend.port.in.addUser.SignupUseCase;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupUseCaseImpl implements SignupUseCase {

    private final SignupAppMapper signupAppMapper;
    private final UserService userService;

    @Override
    @Transactional
    public AddUserAppResponse addUser(AddUserAppRequest appReq) {
        User user = userService.addUser(signupAppMapper.toCommand(appReq));
        return signupAppMapper.toAppRes(user, AddUserAppResponse.class);
    }
}
