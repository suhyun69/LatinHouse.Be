package com.latinhouse.backend.port.in.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetTodoAppResponse {
    private Long no;
    private String todo;
    private Boolean isCompleted;
    private String createdBy;
}
