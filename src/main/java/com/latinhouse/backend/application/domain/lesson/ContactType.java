package com.latinhouse.backend.application.domain.lesson;

import java.util.Arrays;

public enum ContactType {
    Phone("P"),
    Kakaotalk("K"),
    Web("W"),
    Instagram("I")
    ;

    private final String code;

    ContactType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ContactType of(String code) {
        return Arrays.stream(values())
                .filter(type -> type.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid ContactType value: " + code));
    }
}
