package com.latinhouse.backend.domain.profile.service;

import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.port.out.profile.CreateProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CreateProfilePort createProfilePort;

    public Profile create(AddProfileCommand cmd) { return createProfilePort.create(Profile.from(cmd)); }
}
