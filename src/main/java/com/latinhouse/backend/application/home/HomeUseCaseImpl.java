package com.latinhouse.backend.application.home;

import com.latinhouse.backend.application.home.mapper.TodoAppMapper;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.service.TodoService;
import com.latinhouse.backend.port.in.home.GetTodoAppResponse;
import com.latinhouse.backend.port.in.home.HomeUseCase;
import com.latinhouse.backend.port.in.home.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.AddTodoAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeUseCaseImpl implements HomeUseCase {

    private final TodoAppMapper todoAppMapper;
    private final TodoService todoService;

    @Override
    public AddTodoAppResponse addTodo(AddTodoAppRequest appReq) {
        Todo todo = todoService.addtodo(todoAppMapper.toCommand(appReq));
        return todoAppMapper.toAppRes(todo, AddTodoAppResponse.class);
    }

    @Override
    public List<GetTodoAppResponse> getTodos() {
        return todoService.getTodos().stream()
                .map(todo -> todoAppMapper.toAppRes(todo, GetTodoAppResponse.class))
                .collect(Collectors.toList());
    }
}
