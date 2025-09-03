package com.latinhouse.backend.domain;

import com.latinhouse.backend.application.port.out.CreateProfilePort;
import com.latinhouse.backend.application.port.out.ReadProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CreateProfilePort createProfilePort;
    private final ReadProfilePort readProfilePort;

    public Profile addProfile(AddProfileDomainRequest req) {
        return createProfilePort.create(req);
    }
    public List<Profile> search() { return readProfilePort.search(); }
    public Optional<Profile> getProfile(String profileId) { return readProfilePort.getProfileById(profileId); }
}
