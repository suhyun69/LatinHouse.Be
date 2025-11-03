package com.latinhouse.backend.port.out.lesson;

import com.latinhouse.backend.domain.lesson.Lesson;

public interface UpdateLessonPort {
    Lesson update(Lesson toBe);
}
