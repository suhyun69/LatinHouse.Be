package com.latinhouse.backend.domain.user.service;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import com.latinhouse.backend.port.out.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserPort createUserPort;

    public User addUser(AddUserCommand cmd) {

        User user = User.builder()
                .email(cmd.getEmail())
                .password(cmd.getPassword())
                .build();
        return createUserPort.create(user);
    }
}
