package com.latinhouse.backend.domain.user.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddUserCommand extends SelfValidating<AddUserCommand> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @Builder
    public AddUserCommand(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }
}
