package com.latinhouse.backend.application.port.in;

import com.latinhouse.backend.adapter.in.web.AddProfileWebRequest;
import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.domain.Sex;
import com.latinhouse.backend.util.RandomUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddProfileAppRequest extends SelfValidating<AddProfileAppRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    @NotNull(message = "sex cannot be null.")
    Sex sex;

    @Builder
    public AddProfileAppRequest(String email, String password, String nickname, String sex) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.sex = Sex.of(sex);

        this.validateSelf();
    }

    public static AddProfileAppRequest from(AddProfileWebRequest webReq) {
        return AddProfileAppRequest.builder()
                .email(webReq.getEmail())
                .password(webReq.getPassword())
                .nickname(webReq.getNickname())
                .sex(webReq.getSex())
                .build();
    }
}
