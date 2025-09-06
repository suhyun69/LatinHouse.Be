package com.latinhouse.backend.application.port.out.profile;

import com.latinhouse.backend.domain.profile.Profile;

public interface UpdateProfilePort {
    void update(Profile profile);
}
