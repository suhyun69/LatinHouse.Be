package com.latinhouse.backend.common.mapper;

public interface AppToCommandStrategy<A, C> {
    boolean supports(Class<?> appType, Class<?> commandType);
    C toCommand(A appReq);
}