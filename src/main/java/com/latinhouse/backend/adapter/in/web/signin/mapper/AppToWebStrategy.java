package com.latinhouse.backend.adapter.in.web.signin.mapper;

public interface AppToWebStrategy<A, W> {
    boolean supports(Class<?> appType, Class<?> webType);
    W toWebRes(A appRes);
}