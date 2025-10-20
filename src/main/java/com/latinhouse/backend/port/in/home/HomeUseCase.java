package com.latinhouse.backend.port.in.home;

import com.latinhouse.backend.port.in.home.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppResponse;
import com.latinhouse.backend.port.in.home.dto.GetTodoAppResponse;

import java.util.List;

public interface HomeUseCase {
    AddTodoAppResponse addTodo(AddTodoAppRequest appReq);
    List<GetTodoAppResponse> getTodos();
    GetTodoAppResponse done(Long no);
}
