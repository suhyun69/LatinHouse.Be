package com.latinhouse.backend.port.in.dto;

import com.latinhouse.backend.adapter.in.web.signup.dto.AddUserWebRequest;
import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddUserAppRequest extends SelfValidating<AddUserAppRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @Builder
    public AddUserAppRequest(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }

    public static AddUserAppRequest from(AddUserWebRequest webReq) {
        return AddUserAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();
    }
}
