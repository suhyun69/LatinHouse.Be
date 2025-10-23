package com.latinhouse.backend.adapter.out.persistence.profile.mapper;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.profile.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProfilePersistenceMapper {

    private final List<DomainToEntityStrategy<?, ?>> domainToEntityStrategies;
    private final List<EntityToDomainStrategy<?, ?>> entityToDomainStrategies;

    public ProfileJpaEntity toEntity(Profile profile) {
        return dispatchDomainToEntity(profile, ProfileJpaEntity.class);
    }

    public Profile toDomain(ProfileJpaEntity profileT) {
        return dispatchEntityToDomain(profileT, Profile.class);
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