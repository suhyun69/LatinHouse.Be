package com.latinhouse.backend.adapter.out.persistence.user;

import com.latinhouse.backend.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.user.repository.UserRepository;
import com.latinhouse.backend.port.out.user.CreateUserPort;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.out.user.ReadUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort, ReadUserPort {

    private final UserPersistenceMapper userPersistenceMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userPersistenceMapper.toDomain(userRepository.save(userPersistenceMapper.toEntity(user)));
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email)
                .map(userPersistenceMapper::toDomain);
    }
}
