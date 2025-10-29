package com.latinhouse.backend.adapter.in.web.my.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetProfileWebResponse {
    private String id;
    private String nickname;
    private String sex;
    private Boolean isInstructor;
}
