package com.latinhouse.backend.application.port.in.dto;

import com.latinhouse.backend.adapter.in.web.dto.AddProfileWebResponse;
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

    public AddProfileWebResponse toWebRes() {
        return AddProfileWebResponse.builder()
                .profileId(this.profileId)
                .build();
    }
}
