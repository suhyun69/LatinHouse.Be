package com.latinhouse.backend.domain.lesson;

import java.util.Arrays;

public enum Region {
    Hongdae("HD"),
    Gangnam("GN");

    private final String code;

    Region(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Region of(String code) {
        return Arrays.stream(values())
                .filter(region -> region.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid region value: " + code));
    }
}
