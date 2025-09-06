package com.latinhouse.backend.application.port.out.lesson;

import com.latinhouse.backend.application.domain.lesson.Lesson;

public interface CreateLessonPort {
    Lesson create(Lesson lesson);
}
