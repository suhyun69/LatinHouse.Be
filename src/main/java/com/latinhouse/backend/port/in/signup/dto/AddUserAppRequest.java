package com.latinhouse.backend.port.in.signup.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "sex cannot be null.")
    Sex sex;

    @Builder
    public AddUserAppRequest(String email, String password, String sex) {
        this.email = email;
        this.password = password;
        this.sex = Sex.of(sex);

        this.validateSelf();
    }
}
