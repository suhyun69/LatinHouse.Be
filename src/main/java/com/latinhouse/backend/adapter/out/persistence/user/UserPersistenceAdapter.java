package com.latinhouse.backend.adapter.out.persistence.user;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.user.repository.UserRepository;
import com.latinhouse.backend.port.out.CreateUserPort;
import com.latinhouse.backend.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements CreateUserPort {

    private final UserPersistenceMapper userPersistenceMapper;
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userPersistenceMapper.toDomain(userRepository.save(userPersistenceMapper.toEntity(user)));
    }
}
