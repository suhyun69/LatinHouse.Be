package com.latinhouse.backend.port.in.signin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SigninAppResponse {
    private String token;
}
