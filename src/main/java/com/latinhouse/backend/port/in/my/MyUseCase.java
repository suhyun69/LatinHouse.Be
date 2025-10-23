package com.latinhouse.backend.port.in.my;

import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;

public interface MyUseCase {
    AddProfileAppResponse generateProfile(AddProfileAppRequest appReq);
}
