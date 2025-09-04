package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.AddProfileCommand;
import com.latinhouse.backend.domain.Profile;

public interface CreateProfilePort {
    Profile create(Profile profile);
}
