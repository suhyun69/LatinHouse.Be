package com.latinhouse.backend.adapter.in.web.checkout.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetCheckoutWebRequest {
    @NotBlank(message = "checkoutId cannot be blank.")
    private String checkoutId;
}
