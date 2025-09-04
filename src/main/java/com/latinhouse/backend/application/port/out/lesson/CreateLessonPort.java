package com.latinhouse.backend.application.port.out.lesson;

import com.latinhouse.backend.domain.lesson.Lesson;

public interface CreateLessonPort {
    Lesson create(Lesson lesson);
}
