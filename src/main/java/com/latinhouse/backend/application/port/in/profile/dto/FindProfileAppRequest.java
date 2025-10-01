package com.latinhouse.backend.application.port.in.profile.dto;

import com.latinhouse.backend.application.domain.profile.Sex;
import com.latinhouse.backend.common.SelfValidating;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class FindProfileAppRequest  extends SelfValidating<AddProfileAppRequest> {
    String sex;
    Boolean isInstructor;

    @Builder
    public FindProfileAppRequest(String sex, Boolean isInstructor) {
        this.sex = sex;
        this.isInstructor = isInstructor;

        this.validateSelf();
    }
}
