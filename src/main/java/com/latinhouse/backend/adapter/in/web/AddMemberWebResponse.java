package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.application.port.in.AddMemberAppResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddMemberWebResponse {
    private String email;

    public static AddMemberWebResponse from(AddMemberAppResponse addMemberAppResponse) {
        return AddMemberWebResponse.builder()
                .email(addMemberAppResponse.getEmail())
                .build();
    }
}
