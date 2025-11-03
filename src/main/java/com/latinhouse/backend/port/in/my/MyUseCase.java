package com.latinhouse.backend.port.in.my;

import com.latinhouse.backend.port.in.my.dto.*;

import java.util.List;

public interface MyUseCase {
    AddProfileAppResponse generateProfile(AddProfileAppRequest appReq);
    List<GetProfileAppResponse> getProfiles(String email);
    void assignProfile(AssignProfileAppRequest appReq);
    void enrollInstructor(EnrollInstructorAppRequest appReq);

    AddLessonAppResponse addLesson(AddLessonAppRequest appReq);
    List<GetLessonAppResponse> getLessons();
}
