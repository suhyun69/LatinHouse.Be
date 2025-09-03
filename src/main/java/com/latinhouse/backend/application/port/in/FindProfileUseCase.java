package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.application.port.in.dto.ProfileAppResponse;

import java.util.List;

public interface FindProfileUseCase {
    List<ProfileAppResponse> search();
}
