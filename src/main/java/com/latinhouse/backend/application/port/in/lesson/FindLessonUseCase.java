package com.latinhouse.backend.application.port.in.lesson;

import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;

import java.util.List;

public interface FindLessonUseCase {
    List<LessonAppResponse> search();
    LessonAppResponse getLesson(Long LessonNo);
}
