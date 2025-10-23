package com.latinhouse.backend.adapter.out.persistence.profile.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.Sex;
import org.springframework.stereotype.Component;

@Component
public class ProfileStrategy implements
        DomainToEntityStrategy<Profile, ProfileJpaEntity>,
        EntityToDomainStrategy<ProfileJpaEntity, Profile> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
        return Profile.class.isAssignableFrom(c)
                && ProfileJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public ProfileJpaEntity toEntity(Profile profile) {
        return ProfileJpaEntity.builder()
                .id(profile.getId())
                .email(profile.getEmail())
                .nickname(profile.getNickname())
                .isInstructor(profile.getIsInstructor())
                .build();
    }

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
        return ProfileJpaEntity.class.isAssignableFrom(c)
                && Profile.class.isAssignableFrom(d);
    }

    @Override
    public Profile toDomain(ProfileJpaEntity profileT) {
        return Profile.builder()
                .id(profileT.getId())
                .email(profileT.getEmail())
                .nickname(profileT.getNickname())
                .isInstructor(profileT.getIsInstructor())
                .build();
    }
}
