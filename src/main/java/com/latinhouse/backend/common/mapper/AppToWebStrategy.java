package com.latinhouse.backend.common.mapper;

public interface AppToWebStrategy<A, W> {
    boolean appToWebSupports(Class<?> appType, Class<?> webType);
    W toWebRes(A appRes);
}