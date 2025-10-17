package com.latinhouse.backend.adapter.out.persistence.user.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.user.Role;
import com.latinhouse.backend.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserStrategy implements
        DomainToEntityStrategy<User, UserJpaEntity>,
        EntityToDomainStrategy<UserJpaEntity, User> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
        return User.class.isAssignableFrom(c)
                && UserJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public UserJpaEntity toEntity(User user) {
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().getCode())
                .build();
    }

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
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
