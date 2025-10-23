package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddProfileAppRequest extends SelfValidating<AddProfileAppRequest> {

    String email;

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    Sex sex;

    @Builder
    public AddProfileAppRequest(String email, String nickname, Sex sex) {
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;

        this.validateSelf();
    }
}