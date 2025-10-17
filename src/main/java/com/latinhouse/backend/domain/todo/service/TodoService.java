package com.latinhouse.backend.domain.todo.service;

import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.port.out.todo.CreateTodoPort;
import com.latinhouse.backend.port.out.todo.ReadTodoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final CreateTodoPort createTodoPort;
    private final ReadTodoPort readTodoPort;

    public Todo addtodo(AddTodoCommand cmd) {
        return createTodoPort.create(Todo.from(cmd));
    }

    public List<Todo> getTodos() {
        return readTodoPort.readTodos();
    }
}
