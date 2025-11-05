package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.profile.Sex;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddProfileAppRequest extends SelfValidating<AddProfileAppRequest> {

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    @Builder
    public AddProfileAppRequest(String nickname) {
        this.nickname = nickname;

        this.validateSelf();
    }
}