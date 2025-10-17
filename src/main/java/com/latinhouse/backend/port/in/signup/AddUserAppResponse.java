package com.latinhouse.backend.port.in.signup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddUserAppResponse {
    private String email;
}
