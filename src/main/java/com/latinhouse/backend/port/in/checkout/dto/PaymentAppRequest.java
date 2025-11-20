package com.latinhouse.backend.port.in.checkout.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class PaymentAppRequest extends SelfValidating<ApplyLessonAppRequest> {

    @NotBlank(message = "checkoutId cannot be blank.")
    String checkoutId;
    List<Long> discounts;

    @Builder
    public PaymentAppRequest(String checkoutId, List<Long>  discounts) {
        this.checkoutId = checkoutId;
        this.discounts = discounts;

        this.validateSelf();
    }
}
