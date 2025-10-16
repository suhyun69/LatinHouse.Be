package com.latinhouse.backend.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SigninAppResponse {
    private String token;
}
