package com.latinhouse.backend.application.port.out.lesson;

import com.latinhouse.backend.application.domain.lesson.Lesson;

import java.util.List;
import java.util.Optional;

public interface ReadLessonPort {
    List<Lesson> findAll();
    Optional<Lesson> findById(Long no);
}
