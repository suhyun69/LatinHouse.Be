package com.latinhouse.backend.adapter.out.persistence.todo;

import com.latinhouse.backend.adapter.out.persistence.todo.mapper.TodoPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.todo.repository.TodoRepository;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.port.out.todo.CreateTodoPort;
import com.latinhouse.backend.port.out.todo.ReadTodoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TodoPersistenceAdapter implements CreateTodoPort, ReadTodoPort {

    private final TodoPersistenceMapper todoPersistenceMapper;
    private final TodoRepository todoRepository;

    @Override
    public Todo create(Todo todo) {
        return todoPersistenceMapper.toDomain(todoRepository.save(todoPersistenceMapper.toEntity(todo)));
    }

    @Override
    public List<Todo> readTodos() {
        return todoRepository.findAll().stream().map(todoPersistenceMapper::toDomain).collect(Collectors.toList());
    }
}
