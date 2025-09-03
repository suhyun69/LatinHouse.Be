package com.latinhouse.backend.application.port.in;

public interface SignupUseCase {
    AddMemberAppResponse addByEmail(AddMemberAppRequest appReq);
}
