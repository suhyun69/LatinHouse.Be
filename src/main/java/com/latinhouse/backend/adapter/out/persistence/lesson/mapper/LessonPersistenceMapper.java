package com.latinhouse.backend.adapter.out.persistence.lesson.mapper;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonPersistenceMapper {

    private final List<DomainToEntityStrategy<?, ?>> domainToEntityStrategies;
    private final List<EntityToDomainStrategy<?, ?>> entityToDomainStrategies;

    public LessonJpaEntity toEntity(Lesson lesson) {
        return dispatchDomainToEntity(lesson, LessonJpaEntity.class);
    }

    public Lesson toDomain(LessonJpaEntity entity) {
        return dispatchEntityToDomain(entity, Lesson.class);
    }

    @SuppressWarnings("unchecked")
    private <D, E> E dispatchDomainToEntity(D domain, Class<E> entityType) {
        var s = (DomainToEntityStrategy<D, E>) domainToEntityStrategies.stream()
                .filter(st -> st.domainToEntitySupports(domain.getClass(), entityType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No DomainToEntityStrategy for %s -> %s".formatted(domain.getClass().getSimpleName(), entityType.getSimpleName())));
        return s.toEntity(domain);
    }

    @SuppressWarnings("unchecked")
    private <E, D> D dispatchEntityToDomain(E entity, Class<D> domainType) {
        var s = (EntityToDomainStrategy<E, D>) entityToDomainStrategies.stream()
                .filter(st -> st.entityToDomainSupports(entity.getClass(), domainType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No EntityToDomainStrategy for %s -> %s".formatted(entity.getClass().getSimpleName(), domainType.getSimpleName())));
        return s.toDomain(entity);
    }
}
