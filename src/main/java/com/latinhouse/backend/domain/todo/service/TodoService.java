package com.latinhouse.backend.domain.todo.service;

import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.port.out.CreateTodoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final CreateTodoPort createTodoPort;

    public Todo addtodo(AddTodoCommand cmd) {
        return createTodoPort.create(Todo.from(cmd));
    }
}
