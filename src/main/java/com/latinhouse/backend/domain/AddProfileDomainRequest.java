package com.latinhouse.backend.domain;

import com.latinhouse.backend.application.port.in.dto.AddProfileAppRequest;
import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.util.RandomUtil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddProfileDomainRequest extends SelfValidating<AddProfileDomainRequest> {

    @NotBlank(message = "email cannot be blank.")
    String email;

    @NotBlank(message = "password cannot be blank.")
    String password;

    String profileId;

    @NotBlank(message = "nickname cannot be blank.")
    String nickname;

    @NotNull(message = "sex cannot be null.")
    Sex sex;

    Boolean isInstructor;

    @Builder
    public AddProfileDomainRequest(String email, String password, String nickname, Sex sex) {
        this.email = email;
        this.password = password;
        this.profileId = RandomUtil.generateRandomId();
        this.nickname = nickname;
        this.sex = sex;
        this.isInstructor = false;

        this.validateSelf();
    }

    public static AddProfileDomainRequest from(AddProfileAppRequest appReq) {
        return AddProfileDomainRequest.builder()
                .email(appReq.getEmail())
                .password(appReq.getPassword())
                .nickname(appReq.getNickname())
                .sex(appReq.getSex())
                .build();
    }
}
