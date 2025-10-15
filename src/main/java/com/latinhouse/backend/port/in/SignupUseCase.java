package com.latinhouse.backend.port.in;

import com.latinhouse.backend.port.in.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.dto.AddUserAppResponse;

public interface SignupUseCase {
    AddUserAppResponse addUser(AddUserAppRequest appReq);
}
