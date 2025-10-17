package com.latinhouse.backend.common.mapper;

public interface DomainToEntityStrategy<D, E> {
    boolean domainToEntitySupports(Class<?> domainType, Class<?> entityType);
    E toEntity(D domain);
}