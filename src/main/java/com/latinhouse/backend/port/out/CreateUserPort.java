package com.latinhouse.backend.port.out;

import com.latinhouse.backend.application.domain.user.AddUserCommand;
import com.latinhouse.backend.application.domain.user.User;

public interface CreateUserPort {
    User create(AddUserCommand appReq);
}
