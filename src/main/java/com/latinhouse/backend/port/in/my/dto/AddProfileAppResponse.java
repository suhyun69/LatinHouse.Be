package com.latinhouse.backend.port.in.my.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddProfileAppResponse {
    private String id;
    private String nickname;
}
