package com.latinhouse.backend.port.out.todo;

import com.latinhouse.backend.domain.todo.Todo;

import java.util.List;

public interface ReadTodoPort {
    List<Todo> readTodos();
    Todo readTodo(Long no);
}
