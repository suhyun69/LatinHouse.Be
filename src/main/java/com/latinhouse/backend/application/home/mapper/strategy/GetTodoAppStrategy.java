package com.latinhouse.backend.application.home.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.port.in.home.dto.GetTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class GetTodoAppStrategy implements DomainToAppStrategy<Todo, GetTodoAppResponse> {

    @Override
    public boolean domainToAppSupports(Class<?> d, Class<?> a) {
        return Todo.class.isAssignableFrom(d)
                && GetTodoAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public GetTodoAppResponse toAppRes(Todo todo) {
        return GetTodoAppResponse.builder()
                .no(todo.getNo())
                .todo(todo.getTodo())
                .isCompleted(todo.getIsCompleted())
                .createdBy(todo.getCreatedBy())
                .build();
    }
}