package com.latinhouse.backend.port.out.profile;

import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.user.User;

public interface UpdateProfilePort {
    Profile update(Profile toBe);
}
