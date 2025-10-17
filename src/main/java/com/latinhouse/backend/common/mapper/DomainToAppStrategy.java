package com.latinhouse.backend.common.mapper;

public interface DomainToAppStrategy<D, A> {
    boolean supports(Class<?> domainType, Class<?> appType);
    A toAppRes(D domain);
}