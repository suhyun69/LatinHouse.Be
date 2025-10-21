package com.latinhouse.backend.port.in.signin;

import com.latinhouse.backend.port.in.signin.dto.SigninAppRequest;
import com.latinhouse.backend.port.in.signin.dto.SigninAppResponse;

public interface SigninUseCase {
    SigninAppResponse signin(SigninAppRequest appReq);
}
