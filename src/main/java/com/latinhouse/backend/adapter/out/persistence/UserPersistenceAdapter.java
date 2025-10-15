package com.latinhouse.backend.adapter.out.persistence;

import com.latinhouse.backend.adapter.out.persistence.entity.UserJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.mapper.UserMapper;
import com.latinhouse.backend.adapter.out.persistence.repository.UserRepository;
import com.latinhouse.backend.port.out.CreateUserPort;
import com.latinhouse.backend.application.domain.user.AddUserCommand;
import com.latinhouse.backend.application.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User create(AddUserCommand req) {

        UserJpaEntity userT = UserJpaEntity.builder()
                .email(req.getEmail())
                .password(req.getPassword())
                .build();
        return userMapper.mapToDomainEntity(userRepository.save(userT));
    }
}
