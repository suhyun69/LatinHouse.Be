package com.latinhouse.backend.application.home.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddTodoStrategy implements
        AppToCommandStrategy<AddTodoAppRequest, AddTodoCommand>,
        DomainToAppStrategy<Todo, AddTodoAppResponse> {

    @Override
    public boolean appToCommandSupports(Class<?> a, Class<?> c) {
        return AddTodoAppRequest.class.isAssignableFrom(a)
                && AddTodoCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddTodoCommand toCommand(AddTodoAppRequest appReq) {
        return AddTodoCommand.builder()
                .todo(appReq.getTodo())
                .build();
    }

    @Override
    public boolean domainToAppSupports(Class<?> d, Class<?> a) {
        return Todo.class.isAssignableFrom(d)
                && AddTodoAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public AddTodoAppResponse toAppRes(Todo todo) {
        return AddTodoAppResponse.builder()
                .no(todo.getNo())
                .build();
    }
}
