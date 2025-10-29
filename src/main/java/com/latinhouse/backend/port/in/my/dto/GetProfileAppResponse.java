package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.domain.profile.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetProfileAppResponse {
    private String id;
    private String nickname;
    private Sex sex;
    private Boolean isInstructor;
}
