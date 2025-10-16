package com.latinhouse.backend.domain.user;

import com.latinhouse.backend.domain.user.dto.AddUserCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;

    public static User from(AddUserCommand command) {
        return User.builder()
                .email(command.getEmail())
                .password(command.getPassword()).build();
    }
}
