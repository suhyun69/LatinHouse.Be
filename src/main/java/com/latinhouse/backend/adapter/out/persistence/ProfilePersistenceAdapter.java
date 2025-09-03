package com.latinhouse.backend.adapter.out.persistence;

import com.latinhouse.backend.adapter.out.persistence.entity.ProfileJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.mapper.ProfileMapper;
import com.latinhouse.backend.adapter.out.persistence.repository.ProfileRepository;
import com.latinhouse.backend.application.port.out.CreateProfilePort;
import com.latinhouse.backend.application.port.out.ReadProfilePort;
import com.latinhouse.backend.domain.AddProfileDomainRequest;
import com.latinhouse.backend.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfilePersistenceAdapter implements CreateProfilePort, ReadProfilePort {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    @Override
    public Profile create(AddProfileDomainRequest req) {

        ProfileJpaEntity profileT = ProfileJpaEntity.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .profileId(req.getProfileId())
                .nickname(req.getNickname())
                .sex(req.getSex().getCode())
                .isInstructor(req.getIsInstructor())
                .build();
        return profileMapper.mapToDomainEntity(profileRepository.save(profileT));
    }

    @Override
    public List<Profile> search() {
        return profileRepository.findAll().stream()
                .map(profileMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<Profile> getProfileById(String profileId) {
        return profileRepository.findByProfileId(profileId)
                .map(profileMapper::mapToDomainEntity);
    }
}