package com.latinhouse.backend.application.port.in.profile.impl;

import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.profile.SignupUseCase;
import com.latinhouse.backend.application.port.in.profile.mapper.ProfileMapper;
import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupImpl implements SignupUseCase {

    private final ProfileMapper profileMapper;
    private final ProfileService profileService;

    @Override
    @Transactional
    public AddProfileAppResponse addByEmail(AddProfileAppRequest appReq) {
        Profile profile = profileService.addProfile(profileMapper.toCommand(appReq));
        return profileMapper.toAddProfileAppRes(profile);
    }
}
