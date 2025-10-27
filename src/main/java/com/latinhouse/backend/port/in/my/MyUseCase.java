package com.latinhouse.backend.port.in.my;

import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import com.latinhouse.backend.port.in.my.dto.GetProfileAppResponse;
import com.latinhouse.backend.port.in.my.dto.SetProfileAppRequest;

import java.util.List;

public interface MyUseCase {
    AddProfileAppResponse generateProfile(AddProfileAppRequest appReq);
    List<GetProfileAppResponse> getProfiles(String email);
    void setProfile(SetProfileAppRequest appReq);
    void enrollInstructor(String profileId);
}
