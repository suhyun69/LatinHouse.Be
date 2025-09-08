package com.latinhouse.backend.application.domain.profile.service;

import com.latinhouse.backend.application.port.out.profile.CreateProfilePort;
import com.latinhouse.backend.application.port.out.profile.ReadProfilePort;
import com.latinhouse.backend.application.port.out.profile.UpdateProfilePort;
import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;

    private final CreateProfilePort createProfilePort;
    private final ReadProfilePort readProfilePort;
    private final UpdateProfilePort updateProfilePort;

    public Profile addProfile(AddProfileCommand cmd) {
        return createProfilePort.create(profileMapper.toDomain(cmd));
    }

    public List<Profile> search() { return readProfilePort.findAll(); }
    public Optional<Profile> getProfile(String profileId) { return readProfilePort.getProfileById(profileId); }

    public void update(Profile profile) {
        updateProfilePort.update(profile);
    }
}
