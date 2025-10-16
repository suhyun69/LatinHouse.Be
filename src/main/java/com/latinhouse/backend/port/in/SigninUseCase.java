package com.latinhouse.backend.port.in;

import com.latinhouse.backend.port.in.dto.SigninAppRequest;
import com.latinhouse.backend.port.in.dto.SigninAppResponse;

public interface SigninUseCase {
    SigninAppResponse signin(SigninAppRequest appReq);
}
