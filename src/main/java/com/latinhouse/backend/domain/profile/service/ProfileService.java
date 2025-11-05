package com.latinhouse.backend.domain.profile.service;

import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.out.profile.CreateProfilePort;
import com.latinhouse.backend.port.out.profile.ReadProfilePort;
import com.latinhouse.backend.port.out.profile.UpdateProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CreateProfilePort createProfilePort;
    private final ReadProfilePort readProfilePort;
    private final UpdateProfilePort updateProfilePort;

    public Profile create(AddProfileCommand cmd) { return createProfilePort.create(Profile.from(cmd)); }

    public Optional<Profile> getProfile(String id) { return readProfilePort.getProfile(id); }

    public List<Profile> getProfiles(String email) {
        return readProfilePort.getProfiles(email);
    }

    public void enrollInstructor(Profile profile) {
        profile.setIsInstructor(true);
        updateProfilePort.update(profile);
    }
}
