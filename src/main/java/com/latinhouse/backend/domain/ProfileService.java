package com.latinhouse.backend.domain;

import com.latinhouse.backend.application.port.out.CreateProfilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CreateProfilePort createProfilePort;

    public Profile addProfile(AddProfileDomainRequest req) {
        return createProfilePort.create(req);
    }
}
