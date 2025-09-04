package com.latinhouse.backend.adapter.out.persistence;

import com.latinhouse.backend.adapter.out.persistence.entity.ProfileJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.mapper.ProfileMapper;
import com.latinhouse.backend.adapter.out.persistence.repository.ProfileRepository;
import com.latinhouse.backend.application.port.out.CreateProfilePort;
import com.latinhouse.backend.application.port.out.ReadProfilePort;
import com.latinhouse.backend.application.port.out.UpdateProfilePort;
import com.latinhouse.backend.domain.AddProfileCommand;
import com.latinhouse.backend.domain.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfilePersistenceAdapter implements CreateProfilePort, ReadProfilePort, UpdateProfilePort {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    @Override
    public Profile create(Profile profile) {
        ProfileJpaEntity profileT = profileMapper.mapToJpaEntity(profile);
        return profileMapper.mapToDomainEntity(profileRepository.save(profileT));
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<Profile> getProfileById(String profileId) {
        return profileRepository.findByProfileId(profileId)
                .map(profileMapper::mapToDomainEntity);
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profileMapper.mapToJpaEntity(profile));
    }
}