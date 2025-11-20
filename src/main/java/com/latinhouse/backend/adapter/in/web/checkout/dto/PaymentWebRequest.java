package com.latinhouse.backend.adapter.in.web.checkout.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWebRequest {
    private List<Long> discounts;
}
