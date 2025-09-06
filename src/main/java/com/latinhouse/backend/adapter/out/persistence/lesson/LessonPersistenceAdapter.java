package com.latinhouse.backend.adapter.out.persistence.lesson;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonT;
import com.latinhouse.backend.adapter.out.persistence.lesson.mapper.LessonMapper;
import com.latinhouse.backend.adapter.out.persistence.lesson.repository.LessonRepository;
import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.application.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonPersistenceAdapter implements CreateLessonPort, ReadLessonPort {

    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        LessonT lessonT = lessonMapper.toEntity(lesson);
        return lessonMapper.toDomain(lessonRepository.save(lessonT));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toDomain)
                .toList();
    }
}
