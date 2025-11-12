package com.latinhouse.backend.port.in.checkout.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetCheckoutAppRequest  extends SelfValidating<ApplyLessonAppRequest> {

    @NotBlank(message = "checkoutId cannot be blank.")
    String checkoutId;

    @Builder
    public GetCheckoutAppRequest(String checkoutId) {
        this.checkoutId = checkoutId;

        this.validateSelf();
    }
}
