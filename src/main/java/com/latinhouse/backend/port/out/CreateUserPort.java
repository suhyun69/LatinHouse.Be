package com.latinhouse.backend.port.out;

import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import com.latinhouse.backend.domain.user.User;

public interface CreateUserPort {
    User create(User user);
}
