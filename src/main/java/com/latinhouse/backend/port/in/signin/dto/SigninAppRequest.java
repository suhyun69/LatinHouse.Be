package com.latinhouse.backend.port.in.signin.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class SigninAppRequest extends SelfValidating<SigninAppRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @Builder
    public SigninAppRequest(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }
}