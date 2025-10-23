package com.latinhouse.backend.adapter.out.persistence.profile;

import com.latinhouse.backend.adapter.out.persistence.profile.mapper.ProfilePersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.profile.repository.ProfileRepository;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.port.out.profile.CreateProfilePort;
import com.latinhouse.backend.port.out.profile.ReadProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfilePersistenceAdapter implements CreateProfilePort, ReadProfilePort {

    private final ProfilePersistenceMapper profilePersistenceMapper;
    private final ProfileRepository profileRepository;

    @Override
    public Profile create(Profile profile) {
        return profilePersistenceMapper.toDomain(profileRepository.save(profilePersistenceMapper.toEntity(profile)));
    }

    @Override
    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id)
                .map(profilePersistenceMapper::toDomain);
    }
}
