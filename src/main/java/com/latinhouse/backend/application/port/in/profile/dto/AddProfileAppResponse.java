package com.latinhouse.backend.application.port.in.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddProfileAppResponse {
    private String profileId;
}
