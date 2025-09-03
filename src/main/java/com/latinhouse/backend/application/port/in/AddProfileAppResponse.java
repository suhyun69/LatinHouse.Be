package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddProfileAppResponse {
    private String profileId;

    public AddProfileAppResponse(Profile profile) {
        this.profileId = profile.getProfileId();
    }
}
