package com.latinhouse.backend.application.port.in.lesson;

import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;

public interface AddLessonUseCase {
    AddLessonAppResponse addLesson(AddLessonAppRequest request);
}
