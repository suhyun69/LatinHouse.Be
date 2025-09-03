package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.AddProfileDomainRequest;
import com.latinhouse.backend.domain.Profile;

public interface CreateProfilePort {
    Profile create(AddProfileDomainRequest appReq);
}
