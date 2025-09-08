package com.latinhouse.backend.application.port.in.lesson.mapper;

public interface DomainToAppStrategy<D, A> {
    boolean supports(Class<?> domainType, Class<?> appType);
    A toAppRes(D domain);
}