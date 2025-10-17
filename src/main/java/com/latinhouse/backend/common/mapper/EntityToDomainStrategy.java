package com.latinhouse.backend.common.mapper;

public interface EntityToDomainStrategy<E, D> {
    boolean supports(Class<?> entityType, Class<?> domainType);
    D toDomain(E entity);
}