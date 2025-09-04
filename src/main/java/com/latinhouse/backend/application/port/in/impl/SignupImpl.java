package com.latinhouse.backend.application.port.in.impl;

import com.latinhouse.backend.application.port.in.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.dto.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.SignupUseCase;
import com.latinhouse.backend.domain.AddProfileCommand;
import com.latinhouse.backend.domain.Profile;
import com.latinhouse.backend.domain.ProfileService;
import com.latinhouse.backend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupImpl implements SignupUseCase {

    private final ProfileService profileService;

    @Override
    @Transactional
    public AddProfileAppResponse addByEmail(AddProfileAppRequest appReq) {

        AddProfileCommand cmd = AddProfileCommand.builder()
            .email(appReq.getEmail())
            .password(appReq.getPassword())
            .nickname(appReq.getNickname())
            .sex(appReq.getSex())
            .build();
        Profile profile = profileService.addProfile(cmd);

        return AddProfileAppResponse.from(profile);
    }
}
