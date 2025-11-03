package com.latinhouse.backend.domain.lesson;

import java.util.Arrays;

public enum DiscountType {
    Earlybird("E"),
    Sex("S");

    private final String code;

    DiscountType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DiscountType of(String code) {
        return Arrays.stream(values())
                .filter(type -> type.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid DiscountType value: " + code));
    }
}