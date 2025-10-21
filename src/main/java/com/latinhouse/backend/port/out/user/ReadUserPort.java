package com.latinhouse.backend.port.out.user;

import com.latinhouse.backend.domain.user.User;

import java.util.Optional;

public interface ReadUserPort {
    Optional<User> getUser(String email);
}
