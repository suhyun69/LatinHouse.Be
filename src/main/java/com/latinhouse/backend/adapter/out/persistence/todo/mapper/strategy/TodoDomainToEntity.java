package com.latinhouse.backend.adapter.out.persistence.todo.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.todo.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.adapter.out.persistence.todo.entity.TodoJpaEntity;
import com.latinhouse.backend.domain.todo.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoDomainToEntity implements DomainToEntityStrategy<Todo, TodoJpaEntity> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
        return Todo.class.isAssignableFrom(c)
                && TodoJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public TodoJpaEntity toEntity(Todo todo) {
        return TodoJpaEntity.builder()
                .no(todo.getNo())
                .todo(todo.getTodo())
                .isCompleted(todo.getIsCompleted())
                .build();
    }
}
