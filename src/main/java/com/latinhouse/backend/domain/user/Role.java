package com.latinhouse.backend.domain.user;

import java.util.Arrays;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Role of(String code) {
        return Arrays.stream(values())
                .filter(role -> role.code.equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid role value: " + code));
    }

    public Boolean isAdmin() {
        return this.equals(Role.ADMIN);
    }
}