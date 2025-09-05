package com.latinhouse.backend.adapter.in.web.profile.dto;

import com.latinhouse.backend.application.port.in.profile.dto.AddProfileAppRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddProfileWebRequest {

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @NotBlank(message = "nickname cannot be blank")
    private String nickname;

    @NotBlank(message = "sex cannot be blank")
    @Pattern(regexp = "^[MF]$", message = "sex must be 'M' or 'F'")
    private String sex;

    public AddProfileAppRequest toAppReq() {
        return AddProfileAppRequest.builder()
                .email(this.getEmail())
                .password(this.getPassword())
                .nickname(this.getNickname())
                .sex(this.getSex())
                .build();
    }
}
