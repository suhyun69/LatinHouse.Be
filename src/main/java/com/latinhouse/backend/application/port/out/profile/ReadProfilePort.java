package com.latinhouse.backend.application.port.out.profile;

import com.latinhouse.backend.domain.profile.Profile;

import java.util.List;
import java.util.Optional;

public interface ReadProfilePort {
    List<Profile> findAll();
    Optional<Profile> getProfileById(String id);
}
