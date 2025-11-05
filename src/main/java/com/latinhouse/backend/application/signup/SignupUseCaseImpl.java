package com.latinhouse.backend.application.signup;

import com.latinhouse.backend.application.signup.mapper.SignupAppMapper;
import com.latinhouse.backend.common.exception.ConflictException;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppResponse;
import com.latinhouse.backend.port.in.signup.SignupUseCase;
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

        userService.getUser(appReq.getEmail())
                .ifPresent(u -> {
                    throw new ConflictException("이미 가입된 이메일입니다.");
                });

        User user = userService.addUser(signupAppMapper.toCommand(appReq));
        return signupAppMapper.toAppRes(user, AddUserAppResponse.class);
    }
}
