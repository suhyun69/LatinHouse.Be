package com.latinhouse.backend.port.in.addTodo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddTodoAppResponse {
    private Long no;
}
