package com.latinhouse.backend.adapter.out.persistence.lesson;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.mapper.LessonMapper;
import com.latinhouse.backend.adapter.out.persistence.lesson.repository.LessonRepository;
import com.latinhouse.backend.application.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.domain.lesson.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonPersistenceAdapter implements CreateLessonPort {

    private final LessonMapper lessonMapper;
    private final LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        LessonJpaEntity lessonT = lessonMapper.mapToJpaEntity(lesson);
        return lessonMapper.mapToDomainEntity(lessonRepository.save(lessonT));
    }
}
