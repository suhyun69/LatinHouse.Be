package com.latinhouse.backend.application.domain.profile.mapper;

import com.latinhouse.backend.application.domain.profile.Profile;
import com.latinhouse.backend.application.domain.profile.service.AddProfileCommand;
import com.latinhouse.backend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("profileDomainMapper")
@RequiredArgsConstructor
public class ProfileMapper {
    public Profile toDomain(AddProfileCommand cmd) {
        return Profile.builder()
                .email(cmd.getEmail())
                .password(cmd.getPassword())
                .profileId(RandomUtil.generateRandomId())
                .nickname(cmd.getNickname())
                .sex(cmd.getSex())
                .isInstructor(false)
                .build();
    }
}
