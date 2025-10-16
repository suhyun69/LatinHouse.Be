package com.latinhouse.backend.domain.todo;

import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long no;
    private String todo;
    private Boolean isCompleted;

    public static Todo from(AddTodoCommand command) {
        return Todo.builder()
                .todo(command.getTodo())
                .isCompleted(command.getIsCompleted())
                .build();
    }
}
