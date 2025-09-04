package com.latinhouse.backend.application.port.in.dto;

import com.latinhouse.backend.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddProfileAppResponse {
    private String profileId;

    public static AddProfileAppResponse from (Profile profile) {
        return AddProfileAppResponse.builder()
                .profileId(profile.getProfileId())
                .build();
    }
}
