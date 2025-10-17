package com.latinhouse.backend.common.mapper;

public interface WebToAppStrategy<W, A> {
    boolean supports(Class<?> webType, Class<?> appType);
    A toAppReq(W webReq);
}