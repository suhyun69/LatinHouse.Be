package com.latinhouse.backend.domain.profile;

import com.latinhouse.backend.application.port.out.CreateProfilePort;
import com.latinhouse.backend.application.port.out.ReadProfilePort;
import com.latinhouse.backend.application.port.out.UpdateProfilePort;
import com.latinhouse.backend.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final CreateProfilePort createProfilePort;
    private final ReadProfilePort readProfilePort;
    private final UpdateProfilePort updateProfilePort;

    public Profile addProfile(AddProfileCommand cmd) {

        Profile profile = Profile.builder()
                .email(cmd.getEmail())
                .password(cmd.getPassword())
                .profileId(RandomUtil.generateRandomId())
                .nickname(cmd.getNickname())
                .sex(cmd.getSex())
                .isInstructor(false)
                .build();
        return createProfilePort.create(profile);
    }

    public List<Profile> search() { return readProfilePort.findAll(); }
    public Optional<Profile> getProfile(String profileId) { return readProfilePort.getProfileById(profileId); }

    public void save(Profile profile) {
        updateProfilePort.save(profile);
    }
}
