package com.latinhouse.backend.application.port.in.lesson.mapper;

public interface AppToCommandStrategy<A, C> {
    boolean supports(Class<?> appType, Class<?> commandType);
    C toCommand(A appReq);
}
