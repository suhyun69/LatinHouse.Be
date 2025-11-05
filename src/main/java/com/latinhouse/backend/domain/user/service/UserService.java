package com.latinhouse.backend.domain.user.service;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import com.latinhouse.backend.port.out.user.CreateUserPort;
import com.latinhouse.backend.port.out.user.ReadUserPort;
import com.latinhouse.backend.port.out.user.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CreateUserPort createUserPort;
    private final ReadUserPort readUserPort;
    private final UpdateUserPort updateUserPort;

    public User addUser(AddUserCommand cmd) {
        return createUserPort.create(User.from(cmd));
    }

    public Optional<User> getUser(String email) {
        return readUserPort.getUser(email);
    }

    public Optional<User> getUserByProfile(String profileId) {
        return readUserPort.getUserByProfile(profileId);
    }

    public void assignProfile(User user, String profileId) {
        user.setProfileId(profileId);
        updateUserPort.update(user);
    }
}
