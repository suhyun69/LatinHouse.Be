package com.latinhouse.backend.domain.user.service;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import com.latinhouse.backend.port.out.user.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserPort createUserPort;

    public User addUser(AddUserCommand cmd) {
        return createUserPort.create(User.from(cmd));
    }
}
