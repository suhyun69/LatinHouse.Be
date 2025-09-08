package com.latinhouse.backend.application.domain.lesson.mapper;

public interface CommandToDomainStrategy<C, D> {
    boolean supports(Class<?> commandType, Class<?> domainType);
    D toDomain(C command);
}
