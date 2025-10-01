package com.latinhouse.backend.application.port.in.profile.impl;

import com.latinhouse.backend.application.port.in.profile.FindProfileUseCase;
import com.latinhouse.backend.application.port.in.profile.dto.FindProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.ProfileAppResponse;
import com.latinhouse.backend.application.port.in.profile.mapper.ProfileMapper;
import com.latinhouse.backend.common.exception.ProfileNotFoundException;
import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProfileImpl implements FindProfileUseCase {

    private final ProfileMapper profileMapper;
    private final ProfileService profileService;

    @Override
    public List<ProfileAppResponse> search(FindProfileAppRequest appReq) {
        return profileService.search(appReq).stream()
                .map(profileMapper::toAppRes)
                .toList();
    }

    @Override
    public ProfileAppResponse getProfile(String profileId) {
        Profile profile = profileService.getProfile(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));

        return profileMapper.toAppRes(profile);
    }
}
