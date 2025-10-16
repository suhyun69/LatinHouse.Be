package com.latinhouse.backend.port.out;

import com.latinhouse.backend.domain.todo.Todo;

public interface CreateTodoPort {
    Todo create(Todo todo);
}
