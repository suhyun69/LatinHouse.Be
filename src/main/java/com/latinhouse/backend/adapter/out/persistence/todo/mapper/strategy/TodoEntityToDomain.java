package com.latinhouse.backend.adapter.out.persistence.todo.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.todo.entity.TodoJpaEntity;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoEntityToDomain implements EntityToDomainStrategy<TodoJpaEntity, Todo> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
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
