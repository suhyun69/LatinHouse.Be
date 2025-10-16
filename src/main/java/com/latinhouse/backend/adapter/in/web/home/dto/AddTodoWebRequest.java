package com.latinhouse.backend.adapter.in.web.home.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddTodoWebRequest {
    @NotBlank(message = "todo cannot be blank")
    private String todo;
}
