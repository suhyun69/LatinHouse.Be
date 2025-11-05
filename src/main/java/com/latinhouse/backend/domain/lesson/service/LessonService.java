package com.latinhouse.backend.domain.lesson.service;

import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.command.AddLessonCommand;
import com.latinhouse.backend.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.port.out.lesson.UpdateLessonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final CreateLessonPort createLessonPort;
    private final ReadLessonPort readLessonPort;
    private final UpdateLessonPort updateLessonPort;

    public Lesson create(AddLessonCommand cmd) {
        return createLessonPort.create(Lesson.from(cmd));
    }

    public Optional<Lesson> getLesson(Long no) {
        return readLessonPort.getLesson(no);
    }

    public List<Lesson> getLessons(String profileId) {
        return readLessonPort.getLessons(profileId);
    }

    public Lesson update(Lesson toBe) {
        return updateLessonPort.update(toBe);
    }
}
