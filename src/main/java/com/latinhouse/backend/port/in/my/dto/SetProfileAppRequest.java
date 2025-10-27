package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SetProfileAppRequest extends SelfValidating<SetProfileAppRequest> {

    @NotBlank(message = "profileId cannot be blank.")
    String profileId;

    @NotBlank(message = "email cannot be blank.")
    String email;

    @Builder
    public SetProfileAppRequest(String profileId) {
        this.profileId = profileId;
    }

    public void validate() {
        this.validateSelf();
    }
}