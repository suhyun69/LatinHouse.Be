package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EnrollInstructorAppRequest extends SelfValidating<EnrollInstructorAppRequest> {

    @NotBlank(message = "profileId cannot be blank.")
    String profileId;

    @Builder
    public EnrollInstructorAppRequest(String profileId) {
        this.profileId = profileId;

        this.validateSelf();
    }
}