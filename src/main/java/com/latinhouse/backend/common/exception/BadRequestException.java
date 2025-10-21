package com.latinhouse.backend.common.exception;

public class BadRequestException extends RuntimeException {

    private final int stackTraceIndex; // 추가된 필드

    public BadRequestException(String message) {
        this(message, 1); // 기본값 1
    }

    public BadRequestException(String message, int stackTraceIndex) {
        super(message);
        this.stackTraceIndex = stackTraceIndex;
    }

    public int getStackTraceIndex() {
        return stackTraceIndex;
    }
}