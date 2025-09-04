package com.latinhouse.backend.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    private String email;
    private String password;
    private String profileId;
    private String nickname;
    private Sex sex;
    private Boolean isInstructor;

    public void enrollInstructor() {
        this.isInstructor = true;
    }
}
