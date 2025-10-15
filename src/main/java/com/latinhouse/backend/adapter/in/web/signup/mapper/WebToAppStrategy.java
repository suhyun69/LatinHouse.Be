package com.latinhouse.backend.adapter.in.web.signup.mapper;

public interface WebToAppStrategy<W, A> {
    boolean supports(Class<?> webType, Class<?> appType);
    A toAppReq(W webReq);
}