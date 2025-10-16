package com.latinhouse.backend.adapter.out.persistence.todo;

import com.latinhouse.backend.adapter.out.persistence.todo.mapper.TodoPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.todo.repository.TodoRepository;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.port.out.CreateTodoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements CreateTodoPort {

    private final TodoPersistenceMapper todoPersistenceMapper;
    private final TodoRepository todoRepository;

    @Override
    public Todo create(Todo todo) {
        return todoPersistenceMapper.toDomain(todoRepository.save(todoPersistenceMapper.toEntity(todo)));
    }
}
