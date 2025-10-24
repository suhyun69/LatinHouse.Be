package com.latinhouse.backend.port.out.profile;

import com.latinhouse.backend.domain.profile.Profile;

import java.util.List;
import java.util.Optional;

public interface ReadProfilePort {
    Optional<Profile> getProfile(String id);
    List<Profile> getProfiles(String email);
}
