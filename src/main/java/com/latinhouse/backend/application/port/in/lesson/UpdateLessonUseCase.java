package com.latinhouse.backend.application.port.in.lesson;

import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppResponse;

public interface UpdateLessonUseCase {
    UpdateLessonAppResponse updateLesson(UpdateLessonAppRequest request);
}
