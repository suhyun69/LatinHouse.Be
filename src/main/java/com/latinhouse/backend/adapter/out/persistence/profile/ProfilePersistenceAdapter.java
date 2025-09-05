package com.latinhouse.backend.adapter.out.persistence.profile;

import com.latinhouse.backend.adapter.out.persistence.profile.entity.ProfileT;
import com.latinhouse.backend.adapter.out.persistence.profile.mapper.ProfileMapper;
import com.latinhouse.backend.adapter.out.persistence.profile.repository.ProfileRepository;
import com.latinhouse.backend.application.port.out.profile.CreateProfilePort;
import com.latinhouse.backend.application.port.out.profile.ReadProfilePort;
import com.latinhouse.backend.application.port.out.profile.UpdateProfilePort;
import com.latinhouse.backend.domain.profile.Profile;
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
        ProfileT profileT = profileMapper.mapToEntity(profile);
        return profileMapper.mapToDomain(profileRepository.save(profileT));
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll().stream()
                .map(profileMapper::mapToDomain)
                .toList();
    }

    @Override
    public Optional<Profile> getProfileById(String profileId) {
        return profileRepository.findByProfileId(profileId)
                .map(profileMapper::mapToDomain);
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profileMapper.mapToEntity(profile));
    }
}