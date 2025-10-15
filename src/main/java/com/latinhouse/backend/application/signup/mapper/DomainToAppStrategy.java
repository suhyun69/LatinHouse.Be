package com.latinhouse.backend.application.signup.mapper;

public interface DomainToAppStrategy<D, A> {
    boolean supports(Class<?> domainType, Class<?> appType);
    A toAppRes(D domain);
}