package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.profile.Profile;

public interface CreateProfilePort {
    Profile create(Profile profile);
}
