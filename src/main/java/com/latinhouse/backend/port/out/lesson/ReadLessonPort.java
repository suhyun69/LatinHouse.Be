package com.latinhouse.backend.port.out.lesson;

import com.latinhouse.backend.domain.lesson.Lesson;

import java.util.List;
import java.util.Optional;

public interface ReadLessonPort {
    Optional<Lesson> getLesson(Long no);
    List<Lesson> getLessons(String profileId);
}
