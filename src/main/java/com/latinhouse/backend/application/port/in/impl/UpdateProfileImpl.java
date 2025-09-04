package com.latinhouse.backend.application.port.in.impl;

import com.latinhouse.backend.application.port.in.UpdateProfileUseCase;
import com.latinhouse.backend.common.exception.ProfileNotFoundException;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProfileImpl implements UpdateProfileUseCase {

    private final ProfileService profileService;

    @Override
    @Transactional
    public void enrollInstructor(String profileId) {

        Profile profile = profileService.getProfile(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));
        profile.enrollInstructor();

        profileService.save(profile);
    }
}
