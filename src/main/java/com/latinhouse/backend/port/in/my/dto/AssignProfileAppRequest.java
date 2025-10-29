package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AssignProfileAppRequest extends SelfValidating<AssignProfileAppRequest> {

    @NotBlank(message = "profileId cannot be blank.")
    String profileId;

    @NotBlank(message = "email cannot be blank.")
    String email;

    @Builder
    public AssignProfileAppRequest(String profileId, String email) {
        this.profileId = profileId;
        this.email = email;

        this.validateSelf();
    }
}