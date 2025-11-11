package com.latinhouse.backend.domain.order;

import com.latinhouse.backend.domain.profile.Sex;

import java.util.Arrays;

public enum OrderStatus {
    APPROVAL_REQUESTED("S0"),
    PAYMENT_READY("S1"),
    PAYMENT_COMPLETED("S2");

    private final String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OrderStatus of(String code) {
        return Arrays.stream(values())
            .filter(orderStatus -> orderStatus.code.equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid orderStatus value: " + code));
    }
}