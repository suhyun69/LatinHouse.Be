package com.latinhouse.backend.adapter.out.persistence.user.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.user.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.adapter.out.persistence.user.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.user.Role;
import com.latinhouse.backend.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class EntityToDomain implements EntityToDomainStrategy<UserJpaEntity, User> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
        return UserJpaEntity.class.isAssignableFrom(c)
                && User.class.isAssignableFrom(d);
    }

    @Override
    public User toDomain(UserJpaEntity userT) {
        return User.builder()
                .email(userT.getEmail())
                .password(userT.getPassword())
                .role(Role.of(userT.getRole()))
                .build();
    }
}
