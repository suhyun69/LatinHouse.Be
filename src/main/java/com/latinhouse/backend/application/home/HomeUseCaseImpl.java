package com.latinhouse.backend.application.home;

import com.latinhouse.backend.application.home.mapper.TodoAppMapper;
import com.latinhouse.backend.common.exception.ForbiddenException;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.service.TodoService;
import com.latinhouse.backend.port.in.home.HomeUseCase;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppResponse;
import com.latinhouse.backend.port.in.home.dto.DoneTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.GetTodoAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public GetTodoAppResponse done(DoneTodoAppRequest appReq) {

        Todo todo = todoService.getTodo(appReq.getNo())
                .orElseThrow(() -> new NotFoundException("Todo not found"));

        if(!todo.getCreatedBy().equals(appReq.getEmail()) && !appReq.getIsAdmin())
            throw new ForbiddenException("권한이 없습니다.");

        todo.done();
        todoService.update(todo);

        return todoAppMapper.toAppRes(todo, GetTodoAppResponse.class);
    }
}
