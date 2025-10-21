package com.latinhouse.backend.common.mapper;

public interface WebToAppStrategy<W, A> {
    boolean webToAppSupports(Class<?> webType, Class<?> appType);
    A toAppReq(W webReq);
}