package com.latinhouse.backend.application.addTodo.mapper.strategy;

import com.latinhouse.backend.application.signup.mapper.AppToCommandStrategy;
import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.port.in.dto.AddTodoAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddTodoAppToCommand implements AppToCommandStrategy<AddTodoAppRequest, AddTodoCommand> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(Class<?> a, Class<?> c) {
        return AddTodoAppRequest.class.isAssignableFrom(a)
                && AddTodoCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddTodoCommand toCommand(AddTodoAppRequest appReq) {
        return AddTodoCommand.builder()
                .todo(appReq.getTodo())
                .build();
    }
}
