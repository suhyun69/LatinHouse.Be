package com.latinhouse.backend.port.in.lesson;

import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;

public interface LessonUseCase {
    GetLessonAppResponse getLesson(Long no);
}
