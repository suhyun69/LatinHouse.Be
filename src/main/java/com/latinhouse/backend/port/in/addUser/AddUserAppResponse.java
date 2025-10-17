package com.latinhouse.backend.port.in.addUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddUserAppResponse {
    private String email;
}
