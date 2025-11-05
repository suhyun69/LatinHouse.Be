package com.latinhouse.backend.application.my;

import com.latinhouse.backend.application.my.mapper.MyAppMapper;
import com.latinhouse.backend.common.exception.ConflictException;
import com.latinhouse.backend.common.exception.ForbiddenException;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.domain.profile.Profile;
import com.latinhouse.backend.domain.profile.command.AddProfileCommand;
import com.latinhouse.backend.domain.profile.service.ProfileService;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.service.UserService;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.*;
import com.latinhouse.backend.util.RandomUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyUseCaseImpl implements MyUseCase {

    private final MyAppMapper myAppMapper;
    private final ProfileService profileService;
    private final UserService userService;
    private final LessonService lessonService;

    @Override
    @Transactional
    public AddProfileAppResponse generateProfile(AddProfileAppRequest appReq, User user) {

        if(!StringUtils.isBlank(user.getProfileId())) throw new ConflictException("user have profile");

        AddProfileCommand command = myAppMapper.toCommand(appReq);
        command.setId(RandomUtils.generateRandomId());
        command.setEmail(user.getEmail());
        command.setSex(user.getSex());
        command.validate();
        Profile profile = profileService.create(command);

        userService.assignProfile(user, profile.getId());

        return myAppMapper.toAppRes(profile, AddProfileAppResponse.class);
    }

    @Override
    public GetProfileAppResponse getProfile(User user) {

        if(StringUtils.isBlank(user.getProfileId())) throw new NotFoundException("Profile not found");

        Profile profile = profileService.getProfile(user.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));

        return myAppMapper.toAppRes(profile, GetProfileAppResponse.class);
    }

    @Override
    @Transactional
    public void enrollInstructor(EnrollInstructorAppRequest appReq, User user) {

        Profile profile = profileService.getProfile(appReq.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found"));
        
        if(!user.getProfileId().equals(appReq.getProfileId()))
            throw new ForbiddenException("User not allowed to enroll instructor");

        profileService.enrollInstructor(profile);
    }

    @Override
    @Transactional
    public AddLessonAppResponse addLesson(AddLessonAppRequest appReq) {
        Lesson lesson = lessonService.create(myAppMapper.toCommand(appReq));
        return myAppMapper.toAppRes(lesson, AddLessonAppResponse.class);
    }

    @Override
    public List<GetLessonAppResponse> getLessons() {
        return lessonService.getLessons().stream()
                .map(lesson -> myAppMapper.toAppRes(lesson, GetLessonAppResponse.class))
                .collect(Collectors.toList());
    }
}
