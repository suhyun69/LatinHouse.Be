package com.latinhouse.backend.application.service;

import com.latinhouse.backend.application.port.in.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.SignupUseCase;
import com.latinhouse.backend.domain.AddProfileDomainRequest;
import com.latinhouse.backend.domain.Profile;
import com.latinhouse.backend.domain.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Signup implements SignupUseCase {

    private final ProfileService profileService;

    @Override
    @Transactional
    public AddProfileAppResponse addByEmail(AddProfileAppRequest appReq) {
        AddProfileDomainRequest req = AddProfileDomainRequest.from(appReq);
        Profile profile = profileService.addProfile(req);

        return new AddProfileAppResponse(profile);
    }
}
