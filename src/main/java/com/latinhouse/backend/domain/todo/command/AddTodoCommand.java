package com.latinhouse.backend.domain.todo.command;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddTodoCommand extends SelfValidating<AddTodoCommand> {

    @NotBlank(message = "todo cannot be blank.")
    String todo;

    @NotNull(message = "isCompleted cannot be null.")
    Boolean isCompleted;

    @Builder
    public AddTodoCommand(String todo) {
        this.todo = todo;
        this.isCompleted = false;

        this.validateSelf();
    }
}
