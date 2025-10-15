package com.latinhouse.backend.adapter.out.persistence.mapper;

import com.latinhouse.backend.adapter.out.persistence.entity.UserJpaEntity;
import com.latinhouse.backend.application.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserJpaEntity mapToJpaEntity(User user) {
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User mapToDomainEntity(UserJpaEntity userT) {
        return User.builder()
                .email(userT.getEmail())
                .password(userT.getPassword())
                .build();
    }
}
