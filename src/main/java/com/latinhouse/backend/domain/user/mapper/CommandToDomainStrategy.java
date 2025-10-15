package com.latinhouse.backend.domain.user.mapper;

public interface CommandToDomainStrategy<C, D> {
    boolean supports(Class<?> commandType, Class<?> domainType);
    D toDomain(C command);
}