package com.latinhouse.backend.domain.user.mapper.strategy;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import com.latinhouse.backend.domain.user.mapper.CommandToDomainStrategy;
import org.springframework.stereotype.Component;

@Component
public class AddCommandToDomain implements CommandToDomainStrategy<AddUserCommand, User> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
        return AddUserCommand.class.isAssignableFrom(c)
                && User.class.isAssignableFrom(d);
    }

    @Override
    public User toDomain(AddUserCommand cmd) {
        return User.builder()
                .email(cmd.getEmail())
                .password(cmd.getPassword())
                .build();
    }
}
