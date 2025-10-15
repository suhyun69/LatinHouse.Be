package com.latinhouse.backend.application.domain.user;

import com.latinhouse.backend.port.out.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserPort createUserPort;

    public User addUser(AddUserCommand req) {
        return createUserPort.create(req);
    }
}
