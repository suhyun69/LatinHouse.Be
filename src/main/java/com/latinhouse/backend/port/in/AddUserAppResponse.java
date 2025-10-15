package com.latinhouse.backend.port.in;

import com.latinhouse.backend.application.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddUserAppResponse {
    private String email;

    public AddUserAppResponse(User user) {
        this.email = user.getEmail();
    }
}
