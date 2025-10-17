package com.latinhouse.backend.adapter.out.persistence.todo.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.todo.entity.TodoJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoStrategy implements
        DomainToEntityStrategy<Todo, TodoJpaEntity>,
        EntityToDomainStrategy<TodoJpaEntity, Todo> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
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

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
        return TodoJpaEntity.class.isAssignableFrom(c)
                && Todo.class.isAssignableFrom(d);
    }

    @Override
    public Todo toDomain(TodoJpaEntity userT) {
        return Todo.builder()
                .no(userT.getNo())
                .todo(userT.getTodo())
                .isCompleted(userT.getIsCompleted())
                .build();
    }
}
