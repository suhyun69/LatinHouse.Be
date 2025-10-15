package com.latinhouse.backend.adapter.out.persistence.user.mapper;

public interface EntityToDomainStrategy<E, D> {
    boolean supports(Class<?> entityType, Class<?> domainType);
    D toDomain(E entity);
}