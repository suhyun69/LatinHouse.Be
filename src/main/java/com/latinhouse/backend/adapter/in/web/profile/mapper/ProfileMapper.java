package com.latinhouse.backend.adapter.in.web.profile.mapper;

import com.latinhouse.backend.adapter.in.web.profile.dto.AddProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.profile.dto.AddProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.profile.dto.ProfileWebResponse;
import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.profile.dto.ProfileAppResponse;
import com.latinhouse.backend.domain.profile.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("profileWebMapper")
@RequiredArgsConstructor
public class ProfileMapper {
    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */
    public AddProfileAppRequest toAppReq(AddProfileWebRequest webReq) {
        return AddProfileAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .nickname(webReq.getNickname())
                .sex(Sex.of(webReq.getSex()))
                .build();
    }

    public AddProfileWebResponse toWebRes(AddProfileAppResponse appRes) {
        return AddProfileWebResponse.builder()
                .profileId(appRes.getProfileId())
                .build();
    }

    public ProfileWebResponse toWebRes(ProfileAppResponse appRes) {
        return ProfileWebResponse.builder()
                .profileId(appRes.getProfileId())
                .nickname(appRes.getNickname())
                .sex(appRes.getSex().getCode())
                .isInstructor(appRes.getIsInstructor())
                .build();
    }
}
