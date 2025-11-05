package com.latinhouse.backend.adapter.out.persistence.user;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.user.repository.UserRepository;
import com.latinhouse.backend.port.out.user.CreateUserPort;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.out.user.ReadUserPort;
import com.latinhouse.backend.port.out.user.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort, ReadUserPort, UpdateUserPort {

    private final UserPersistenceMapper userPersistenceMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userPersistenceMapper.toDomain(userRepository.save(userPersistenceMapper.toEntity(user)));
    }

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findById(email)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    public User update(User toBe) {
       return userPersistenceMapper.toDomain(userRepository.save(userPersistenceMapper.toEntity(toBe)));
    }
}
