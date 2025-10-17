package com.latinhouse.backend.port.in.signup;

import com.latinhouse.backend.port.in.signup.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppResponse;

public interface SignupUseCase {
    AddUserAppResponse addUser(AddUserAppRequest appReq);
}
