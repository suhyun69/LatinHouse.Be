package com.latinhouse.backend.adapter.out.persistence.mapper;

import com.latinhouse.backend.adapter.out.persistence.entity.ProfileJpaEntity;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper {
    public ProfileJpaEntity mapToJpaEntity(Profile profile) {
        return ProfileJpaEntity.builder()
                .email(profile.getEmail())
                .password(profile.getPassword())
                .profileId(profile.getProfileId())
                .nickname(profile.getNickname())
                .sex(profile.getSex().getCode())
                .isInstructor(profile.getIsInstructor())
                .build();
    }

    public Profile mapToDomainEntity(ProfileJpaEntity userT) {
        return Profile.builder()
                .email(userT.getEmail())
                .password(userT.getPassword())
                .profileId(userT.getProfileId())
                .nickname(userT.getNickname())
                .sex(Sex.of(userT.getSex()))
                .isInstructor(userT.getIsInstructor())
                .build();
    }
}
