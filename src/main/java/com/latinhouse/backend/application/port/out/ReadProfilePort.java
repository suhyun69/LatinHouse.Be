package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ReadProfilePort {
    List<Profile> findAll();
    Optional<Profile> getProfileById(String id);
}
