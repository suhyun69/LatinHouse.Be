package com.latinhouse.backend.domain.user.mapper;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDomainMapper {

    /*
        command -> domain
     */

    private final List<CommandToDomainStrategy<?, ?>> commandToDomainStrategies;

    public User toDomain(AddUserCommand cmd) {
        return dispatchCommandToDomain(cmd, User.class);
    }

    @SuppressWarnings("unchecked")
    private <C, D> D dispatchCommandToDomain(C cmd, Class<D> domainType) {
        var s = (CommandToDomainStrategy<C, D>) commandToDomainStrategies.stream()
                .filter(st -> st.supports(cmd.getClass(), domainType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No commandToDomainStrategy for %s -> %s".formatted(cmd.getClass().getSimpleName(), domainType.getSimpleName())));
        return s.toDomain(cmd);
    }
}
