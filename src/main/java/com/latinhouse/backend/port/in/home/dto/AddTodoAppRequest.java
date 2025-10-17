package com.latinhouse.backend.port.in.home.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddTodoAppRequest extends SelfValidating<AddTodoAppRequest> {

    @NotBlank(message = "email cannot be blank.")
    String todo;

    @Builder
    public AddTodoAppRequest(String todo) {
        this.todo = todo;

        this.validateSelf();
    }
}
