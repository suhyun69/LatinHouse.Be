package com.latinhouse.backend.common.mapper;

public interface DomainToAppStrategy<D, A> {
    boolean domainToAppSupports(Class<?> domainType, Class<?> appType);
    A toAppRes(D domain);
}