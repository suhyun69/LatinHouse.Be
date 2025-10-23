package com.latinhouse.backend.application.my;

import com.latinhouse.backend.application.my.mapper.MyAppMapper;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUseCaseImpl implements MyUseCase {

    private final MyAppMapper myAppMapper;
    private final ProfileService profileService;

    @Override
    public AddProfileAppResponse generateProfile(AddProfileAppRequest appReq) {
        Profile profile = profileService.create(myAppMapper.toCommand(appReq));
        return myAppMapper.toAppRes(profile, AddProfileAppResponse.class);
    }
}
