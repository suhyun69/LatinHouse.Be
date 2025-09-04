package com.latinhouse.backend.domain.lesson;

import com.latinhouse.backend.domain.profile.Sex;

import java.util.Arrays;

public enum Genre {
    Salsa("S"),
    Bachata("B");

    private final String code;

    Genre(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Genre of(String code) {
        return Arrays.stream(values())
                .filter(genre -> genre.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid genre value: " + code));
    }
}
