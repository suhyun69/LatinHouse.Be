package com.latinhouse.backend.adapter.in.web.signup.dto;

import com.latinhouse.backend.port.in.AddUserAppResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddUserWebResponse {
    private String email;

    public static AddUserWebResponse from(AddUserAppResponse addUserAppResponse) {
        return AddUserWebResponse.builder()
                .email(addUserAppResponse.getEmail())
                .build();
    }
}
