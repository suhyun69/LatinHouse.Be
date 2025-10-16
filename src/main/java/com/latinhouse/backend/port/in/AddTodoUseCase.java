package com.latinhouse.backend.port.in;

import com.latinhouse.backend.port.in.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.dto.AddTodoAppResponse;

public interface AddTodoUseCase {
    AddTodoAppResponse addTodo(AddTodoAppRequest appReq);
}
