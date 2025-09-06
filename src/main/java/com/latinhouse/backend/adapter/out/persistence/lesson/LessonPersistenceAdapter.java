package com.latinhouse.backend.adapter.out.persistence.lesson;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonT;
import com.latinhouse.backend.adapter.out.persistence.lesson.mapper.LessonPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.lesson.repository.LessonRepository;
import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.application.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LessonPersistenceAdapter implements CreateLessonPort, ReadLessonPort {

    private final LessonPersistenceMapper lessonPersistenceMapper;
    private final LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        LessonT lessonT = lessonPersistenceMapper.toEntity(lesson);
        return lessonPersistenceMapper.toDomain(lessonRepository.save(lessonT));
    }

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll().stream()
                .map(lessonPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Lesson> findById(Long no) {
        return lessonRepository.findById(no)
                .map(lessonPersistenceMapper::toDomain);
    }
}
