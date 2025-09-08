package com.latinhouse.backend.application.port.out.lesson;

import com.latinhouse.backend.application.domain.lesson.Lesson;

public interface UpdateLessonPort {
    Lesson update(Lesson lesson);
}
