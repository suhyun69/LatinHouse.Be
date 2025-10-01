package com.latinhouse.backend.application.port.in.profile;

import com.latinhouse.backend.application.port.in.profile.dto.FindProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.ProfileAppResponse;

import java.util.List;

public interface FindProfileUseCase {
    List<ProfileAppResponse> search(FindProfileAppRequest appReq);
    ProfileAppResponse getProfile(String profileId);
}
