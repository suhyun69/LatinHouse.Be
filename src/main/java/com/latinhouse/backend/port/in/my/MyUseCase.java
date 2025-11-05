package com.latinhouse.backend.port.in.my;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.my.dto.*;

import java.util.List;

public interface MyUseCase {
    AddProfileAppResponse generateProfile(AddProfileAppRequest appReq, User user);
    GetProfileAppResponse getProfile(User user);
    void enrollInstructor(EnrollInstructorAppRequest appReq, User user);

    AddLessonAppResponse addLesson(AddLessonAppRequest appReq);
    List<GetLessonAppResponse> getLessons();
}
