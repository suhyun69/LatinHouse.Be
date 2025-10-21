package com.latinhouse.backend.adapter.in.web.signin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SigninWebResponse {
    private String accessToken;
    private String tokenType;
    private String issuedAt;
}
