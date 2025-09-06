package com.latinhouse.backend.application.port.in.profile.mapper;

import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.profile.dto.ProfileAppResponse;
import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.service.AddProfileCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("profilePortMapper")
@RequiredArgsConstructor
public class ProfileMapper {
    /*
        AppRequest -> Command
        Domain -> AppResponse
     */
    public AddProfileCommand toCommand(AddProfileAppRequest appReq) {
        return AddProfileCommand.builder()
                .email(appReq.getEmail())
                .password(appReq.getPassword())
                .nickname(appReq.getNickname())
                .sex(appReq.getSex())
                .build();
    }

    public AddProfileAppResponse toAddProfileAppRes(Profile profile) {
        return AddProfileAppResponse.builder()
                .profileId(profile.getProfileId())
                .build();
    }

    public ProfileAppResponse toAppRes(Profile profile) {
        return ProfileAppResponse.builder()
                .profileId(profile.getProfileId())
                .nickname(profile.getNickname())
                .sex(profile.getSex())
                .isInstructor(profile.getIsInstructor())
                .build();
    }
}
