package com.latinhouse.backend.application.addTodo;

import com.latinhouse.backend.application.addTodo.mapper.AddTodoAppMapper;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.service.TodoService;
import com.latinhouse.backend.port.in.addTodo.AddTodoUseCase;
import com.latinhouse.backend.port.in.addTodo.AddTodoAppRequest;
import com.latinhouse.backend.port.in.addTodo.AddTodoAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddTodoUseCaseImpl implements AddTodoUseCase {

    private final AddTodoAppMapper addTodoAppMapper;
    private final TodoService todoService;

    @Override
    public AddTodoAppResponse addTodo(AddTodoAppRequest appReq) {
        Todo todo = todoService.addtodo(addTodoAppMapper.toCommand(appReq));
        return addTodoAppMapper.toAppRes(todo, AddTodoAppResponse.class);
    }
}
