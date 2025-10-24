package com.latinhouse.backend.application.my.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import com.latinhouse.backend.port.in.my.dto.GetProfileAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetProfilesAppStrategy implements
        DomainToAppStrategy<Profile, GetProfileAppResponse> {

    @Override
    public boolean domainToAppSupports(Class<?> d, Class<?> a) {
        return Profile.class.isAssignableFrom(d)
                && GetProfileAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public GetProfileAppResponse toAppRes(Profile profile) {
        return GetProfileAppResponse.builder()
                .id(profile.getId())
                .nickname(profile.getNickname())
                .isInstructor(profile.getIsInstructor())
                .build();
    }
}
