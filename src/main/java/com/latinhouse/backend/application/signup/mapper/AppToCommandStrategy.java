package com.latinhouse.backend.application.signup.mapper;

public interface AppToCommandStrategy<A, C> {
    boolean supports(Class<?> appType, Class<?> commandType);
    C toCommand(A appReq);
}