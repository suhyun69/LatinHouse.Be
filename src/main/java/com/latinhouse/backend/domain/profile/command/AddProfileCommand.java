package com.latinhouse.backend.domain.profile.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddProfileCommand extends SelfValidating<AddProfileCommand> {

    @NotBlank(message = "id cannot be blank.")
    String id;

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    @NotNull(message = "sex cannot be null.")
    Sex sex;

    Boolean isInstructor;

    @Builder
    public AddProfileCommand(String id, String nickname, Sex sex, Boolean isInstructor) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.isInstructor = isInstructor;

        this.validateSelf();
    }
}
