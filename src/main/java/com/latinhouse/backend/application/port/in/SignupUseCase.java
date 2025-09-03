package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.application.port.in.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.dto.AddProfileAppResponse;

public interface SignupUseCase {
    AddProfileAppResponse addByEmail(AddProfileAppRequest appReq);
}
