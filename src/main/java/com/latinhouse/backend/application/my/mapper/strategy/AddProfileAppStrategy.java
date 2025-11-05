package com.latinhouse.backend.application.my.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddProfileAppStrategy implements
        AppToCommandStrategy<AddProfileAppRequest, AddProfileCommand>,
        DomainToAppStrategy<Profile, AddProfileAppResponse> {

    @Override
    public boolean appToCommandSupports(Class<?> a, Class<?> c) {
        return AddProfileAppRequest.class.isAssignableFrom(a)
                && AddProfileCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddProfileCommand toCommand(AddProfileAppRequest appReq) {
        return AddProfileCommand.builder()
                .nickname(appReq.getNickname())
                .build();
    }

    @Override
    public boolean domainToAppSupports(Class<?> d, Class<?> a) {
        return Profile.class.isAssignableFrom(d)
                && AddProfileAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public AddProfileAppResponse toAppRes(Profile profile) {
        return AddProfileAppResponse.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .build();
    }
}
