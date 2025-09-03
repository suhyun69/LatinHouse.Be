package com.latinhouse.backend.application.port.out;

import com.latinhouse.backend.domain.Profile;

import java.util.List;

public interface ReadProfilePort {
    List<Profile> search();
}
