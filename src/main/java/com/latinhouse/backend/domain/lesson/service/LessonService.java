package com.latinhouse.backend.domain.lesson.service;

import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.application.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonMapper lessonMapper;
    private final CreateLessonPort createLessonPort;
    private final ReadLessonPort readLessonPort;

    public Lesson addLesson(AddLessonCommand cmd) {
        return createLessonPort.create(lessonMapper.toDomain(cmd));
    }

    public List<Lesson> search() { return readLessonPort.findAll(); }
}
