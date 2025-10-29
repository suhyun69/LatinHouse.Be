package com.latinhouse.backend.application.my;

import com.latinhouse.backend.application.my.mapper.MyAppMapper;
import com.latinhouse.backend.common.exception.DuplicateMappingException;
import com.latinhouse.backend.common.exception.ForbiddenException;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.service.UserService;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.*;
import com.latinhouse.backend.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUseCaseImpl implements MyUseCase {

    private final MyAppMapper myAppMapper;
    private final ProfileService profileService;
    private final UserService userService;

    @Override
    public AddProfileAppResponse generateProfile(AddProfileAppRequest appReq) {

        User user = userService.getUser(appReq.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        AddProfileCommand command = myAppMapper.toCommand(appReq);
        command.setId(RandomUtils.generateRandomId());
        command.setSex(user.getSex());
        command.validate();

        Profile profile = profileService.create(command);
        return myAppMapper.toAppRes(profile, AddProfileAppResponse.class);
    }

    @Override
    public List<GetProfileAppResponse> getProfiles(String email) {
        return profileService.getProfiles(email).stream()
                .map(profile -> myAppMapper.toAppRes(profile, GetProfileAppResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void assignProfile(AssignProfileAppRequest appReq) {
        Profile profile = profileService.getProfile(appReq.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        userService.getUserByProfile(profile.getId())
                .ifPresent(existingUser -> {
                    throw new DuplicateMappingException("Profile is already assigned to another user: " + existingUser.getEmail());
                });

        User user = userService.getUser(appReq.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setProfileId(profile.getId());

        userService.update(user);
    }

    @Override
    public void enrollInstructor(EnrollInstructorAppRequest appReq) {
        User user = userService.getUser(appReq.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if(!user.getProfileId().equals(appReq.getProfileId())) throw new ForbiddenException("User not allowed to enroll instructor");

        Profile profile = profileService.getProfile(appReq.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        profile.enrollInstructor();

        profileService.update(profile);
    }
}
