package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.adapter.in.web.AddMemberWebRequest;
import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddMemberAppRequest extends SelfValidating<AddMemberAppRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @Builder
    public AddMemberAppRequest(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }

    public static AddMemberAppRequest from(AddMemberWebRequest webReq) {
        return AddMemberAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .build();
    }
}
