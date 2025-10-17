package com.latinhouse.backend.application.home.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.port.in.home.AddTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddTodoDomainToApp implements DomainToAppStrategy<Todo, AddTodoAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
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