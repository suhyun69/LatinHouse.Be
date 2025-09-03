package com.latinhouse.backend.application.port.in;

public interface SignupUseCase {
    AddProfileAppResponse addByEmail(AddProfileAppRequest appReq);
}
