package com.latinhouse.backend.adapter.in.web.signup.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupWebRequest {

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
