package com.latinhouse.backend.adapter.out.persistence.profile.mapper;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileT;
import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.Sex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("profilePersistenceMapper")
@RequiredArgsConstructor
public class ProfileMapper {
    public ProfileT toEntity(Profile profile) {
        return ProfileT.builder()
                .email(profile.getEmail())
                .password(profile.getPassword())
                .profileId(profile.getProfileId())
                .nickname(profile.getNickname())
                .sex(profile.getSex().getCode())
                .isInstructor(profile.getIsInstructor())
                .build();
    }

    public Profile toDomain(ProfileT profileT) {
        return Profile.builder()
                .email(profileT.getEmail())
                .password(profileT.getPassword())
                .profileId(profileT.getProfileId())
                .nickname(profileT.getNickname())
                .sex(Sex.of(profileT.getSex()))
                .isInstructor(profileT.getIsInstructor())
                .build();
    }
}
