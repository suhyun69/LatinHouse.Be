package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.Profile;

public interface UpdateProfilePort {
    void save(Profile profile);
}
