package com.latinhouse.backend.domain.user;

import com.latinhouse.backend.domain.profile.Sex;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
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
    private Sex sex;
    private Role role;

    public static User from(AddUserCommand command) {
        return User.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .sex(command.getSex())
                .role(command.getRole())
                .build();
    }
}
