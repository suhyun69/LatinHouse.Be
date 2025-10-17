package com.latinhouse.backend.adapter.out.persistence.user.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.user.entity.UserJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.domain.user.User;
import org.springframework.stereotype.Component;

@Component("userDomainToEntity")
public class UserDomainToEntity implements DomainToEntityStrategy<User, UserJpaEntity> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
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
}
