package com.latinhouse.backend.domain.profile.command;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddProfileCommand extends SelfValidating<AddProfileCommand> {

    @NotBlank(message = "id cannot be blank.")
    String id;

    String email;

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    Sex sex;

    Boolean isInstructor;

    @Builder
    public AddProfileCommand(String nickname) {
        this.nickname = nickname;
        this.isInstructor = false;
    }

    public void validate() {
        this.validateSelf();
    }
}
