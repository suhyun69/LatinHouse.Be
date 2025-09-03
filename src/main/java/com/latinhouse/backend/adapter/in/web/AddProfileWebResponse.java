package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.application.port.in.AddProfileAppResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddProfileWebResponse {
    private String profileId;

    public static AddProfileWebResponse from(AddProfileAppResponse addProfileAppResponse) {
        return AddProfileWebResponse.builder()
                .profileId(addProfileAppResponse.getProfileId())
                .build();
    }
}
