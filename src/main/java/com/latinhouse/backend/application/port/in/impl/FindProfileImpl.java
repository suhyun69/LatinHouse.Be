package com.latinhouse.backend.application.port.in.impl;

import com.latinhouse.backend.application.port.in.FindProfileUseCase;
import com.latinhouse.backend.application.port.in.dto.ProfileAppResponse;
import com.latinhouse.backend.common.exception.ProfileNotFoundException;
import com.latinhouse.backend.domain.Profile;
import com.latinhouse.backend.domain.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProfileImpl implements FindProfileUseCase {

    private final ProfileService profileService;

    @Override
    public List<ProfileAppResponse> search() {
        return profileService.search().stream()
                .map(ProfileAppResponse::from)
                .toList();
    }

    @Override
    public ProfileAppResponse getProfile(String profileId) {
        Profile profile = profileService.getProfile(profileId)
                .orElseThrow(() -> new ProfileNotFoundException(profileId));
        return ProfileAppResponse.from(profile);
    }
}
