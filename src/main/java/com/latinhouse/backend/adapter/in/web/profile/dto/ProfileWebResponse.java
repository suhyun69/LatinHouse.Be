package com.latinhouse.backend.adapter.in.web.profile.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileWebResponse {
    private String profileId;
    private String nickname;
    private String sex;
    private Boolean isInstructor;
}
