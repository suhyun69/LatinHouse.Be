package com.latinhouse.backend.application.port.out.lesson;

import com.latinhouse.backend.domain.lesson.Lesson;

import java.util.List;

public interface ReadLessonPort {
    List<Lesson> findAll();
}
