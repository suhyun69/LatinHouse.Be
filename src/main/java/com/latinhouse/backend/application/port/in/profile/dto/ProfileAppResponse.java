package com.latinhouse.backend.application.port.in.profile.dto;

import com.latinhouse.backend.adapter.in.web.profile.dto.ProfileWebResponse;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.Sex;
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

    public ProfileWebResponse toWebRes() {
        return ProfileWebResponse.builder()
                .profileId(this.getProfileId())
                .nickname(this.getNickname())
                .sex(this.getSex() != null ? this.getSex().getCode() : null) // null-safe
                .isInstructor(Boolean.TRUE.equals(this.getIsInstructor()))
                .build();
    }
}
