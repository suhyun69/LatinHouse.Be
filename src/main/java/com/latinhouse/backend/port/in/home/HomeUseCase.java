package com.latinhouse.backend.port.in.home;

import java.util.List;

public interface HomeUseCase {
    AddTodoAppResponse addTodo(AddTodoAppRequest appReq);
    List<GetTodoAppResponse> getTodos();
}
