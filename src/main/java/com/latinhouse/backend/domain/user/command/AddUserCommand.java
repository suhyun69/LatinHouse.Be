package com.latinhouse.backend.domain.user.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import com.latinhouse.backend.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "sex cannot be null.")
    Sex sex;

    @NotNull(message = "genre cannot be null.")
    Role role;

    @Builder
    public AddUserCommand(String email, String password, Sex sex) {
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.role = Role.USER;

        this.validateSelf();
    }
}
