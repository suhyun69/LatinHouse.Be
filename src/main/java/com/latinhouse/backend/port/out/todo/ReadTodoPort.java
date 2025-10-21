package com.latinhouse.backend.port.out.todo;

import com.latinhouse.backend.domain.todo.Todo;

import java.util.List;
import java.util.Optional;

public interface ReadTodoPort {
    List<Todo> readTodos();
    Optional<Todo> readTodo(Long no);
}
