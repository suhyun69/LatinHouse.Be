package com.latinhouse.backend.domain.profile;

import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private String id;
    private String email;
    private String nickname;
    private Boolean isInstructor;

    public static Profile from(AddProfileCommand command) {
        return Profile.builder()
                .id(command.getId())
                .email(command.getEmail())
                .nickname(command.getNickname())
                .isInstructor(command.getIsInstructor())
                .build();
    }
}
