package com.latinhouse.backend.application.port.in.profile.dto;

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
}
