package com.latinhouse.backend.application.domain.lesson.service;

import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.application.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.mapper.LessonDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonDomainMapper lessonDomainMapper;
    private final CreateLessonPort createLessonPort;
    private final ReadLessonPort readLessonPort;

    public Lesson addLesson(AddLessonCommand cmd) {
        return createLessonPort.create(lessonDomainMapper.toDomain(cmd));
    }

    public List<Lesson> search() { return readLessonPort.findAll(); }
    public Optional<Lesson> getLesson(Long lessonNo) { return readLessonPort.findById(lessonNo); }
}
