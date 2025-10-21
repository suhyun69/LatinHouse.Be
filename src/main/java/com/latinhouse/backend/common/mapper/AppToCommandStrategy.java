package com.latinhouse.backend.common.mapper;

public interface AppToCommandStrategy<A, C> {
    boolean appToCommandSupports(Class<?> appType, Class<?> commandType);
    C toCommand(A appReq);
}