package com.latinhouse.backend.port.in;

public interface SignupUseCase {
    AddUserAppResponse addByEmail(AddUserAppRequest appReq);
}
