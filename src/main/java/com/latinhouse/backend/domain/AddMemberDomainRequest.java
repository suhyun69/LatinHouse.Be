package com.latinhouse.backend.domain;

import com.latinhouse.backend.application.port.in.AddMemberAppRequest;
import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddMemberDomainRequest extends SelfValidating<AddMemberDomainRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @Builder
    public AddMemberDomainRequest(String email, String password) {
        this.email = email;
        this.password = password;

        this.validateSelf();
    }

    public static AddMemberDomainRequest from(AddMemberAppRequest appReq) {
        return AddMemberDomainRequest.builder()
                .email(appReq.getEmail())
                .password(appReq.getPassword())
                .build();
    }
}
