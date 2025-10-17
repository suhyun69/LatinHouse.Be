package com.latinhouse.backend.port.out;

import com.latinhouse.backend.domain.todo.Todo;

import java.util.List;

public interface ReadTodoPort {
    List<Todo> readTodos();
}
