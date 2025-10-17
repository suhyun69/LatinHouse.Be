package com.latinhouse.backend.adapter.in.web.home.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTodoWebResponse {
    private Long no;
    private String todo;
    private Boolean isCompleted;
    private String createdBy;
}
