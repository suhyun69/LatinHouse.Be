package com.latinhouse.backend.application.addTodo.mapper;

public interface DomainToAppStrategy<D, A> {
    boolean supports(Class<?> domainType, Class<?> appType);
    A toAppRes(D domain);
}