package com.latinhouse.backend.application.domain.profile.service;

import com.latinhouse.backend.application.domain.profile.Sex;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddProfileCommand {
    String email;
    String password;
    String profileId;
    String nickname;
    Sex sex;
    Boolean isInstructor;
}
