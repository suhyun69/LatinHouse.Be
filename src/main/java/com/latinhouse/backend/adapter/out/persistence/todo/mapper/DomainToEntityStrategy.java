package com.latinhouse.backend.adapter.out.persistence.todo.mapper;

public interface DomainToEntityStrategy<D, E> {
    boolean supports(Class<?> domainType, Class<?> entityType);
    E toEntity(D domain);
}