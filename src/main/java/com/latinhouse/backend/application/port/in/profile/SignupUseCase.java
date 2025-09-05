package com.latinhouse.backend.application.port.in.profile;

import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppResponse;

public interface SignupUseCase {
    AddProfileAppResponse addByEmail(AddProfileAppRequest appReq);
}
