package com.latinhouse.backend.application.addTodo.mapper.strategy;

import com.latinhouse.backend.application.addTodo.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.port.in.dto.AddTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddTodoDomainToApp implements DomainToAppStrategy<Todo, AddTodoAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
        return Todo.class.isAssignableFrom(d)
                && AddTodoAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public AddTodoAppResponse toAppRes(Todo user) {
        return AddTodoAppResponse.builder()
                .no(user.getNo())
                .build();
    }
}