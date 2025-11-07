package com.latinhouse.backend.port.in.lesson;

import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;

public interface LessonUseCase {
    GetLessonAppResponse getLesson(Long lessonNo);
    ApplyLessonAppResponse applyLeson(ApplyLessonAppRequest appReq, User user);
}
