package com.latinhouse.backend.application.port.in.dto;

import com.latinhouse.backend.domain.Profile;
import com.latinhouse.backend.domain.Sex;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileAppResponse {
    private String profileId;
    private String nickname;
    private Sex sex;
    private Boolean isInstructor;

    public static ProfileAppResponse from(Profile profile) {
        return ProfileAppResponse.builder()
                .profileId(profile.getProfileId())
                .nickname(profile.getNickname())
                .sex(profile.getSex())
                .isInstructor(Boolean.TRUE.equals(profile.getIsInstructor()))
                .build();
    }
}
