package com.latinhouse.backend.application.my;

import com.latinhouse.backend.application.my.mapper.MyAppMapper;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import com.latinhouse.backend.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUseCaseImpl implements MyUseCase {

    private final MyAppMapper myAppMapper;
    private final ProfileService profileService;

    @Override
    public AddProfileAppResponse generateProfile(AddProfileAppRequest appReq) {

        AddProfileCommand command = myAppMapper.toCommand(appReq);
        command.setId(RandomUtils.generateRandomId());
        command.validate();

        Profile profile = profileService.create(command);
        return myAppMapper.toAppRes(profile, AddProfileAppResponse.class);
    }
}
