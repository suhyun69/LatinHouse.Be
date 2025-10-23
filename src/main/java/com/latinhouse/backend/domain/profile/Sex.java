package com.latinhouse.backend.domain.profile;

import java.util.Arrays;

public enum Sex {
    Male("M"),
    Female("F");

    private final String code;

    Sex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Sex of(String code) {
        return Arrays.stream(values())
                .filter(sex -> sex.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid sex value: " + code));
    }
}