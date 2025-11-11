package com.latinhouse.backend.adapter.out.persistence.lesson;

import com.latinhouse.backend.adapter.out.persistence.lesson.mapper.LessonPersistenceMapper;
import com.latinhouse.backend.adapter.out.persistence.lesson.repository.LessonRepository;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.port.out.lesson.CreateLessonPort;
import com.latinhouse.backend.port.out.lesson.ReadLessonPort;
import com.latinhouse.backend.port.out.lesson.UpdateLessonPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LessonPersistenceAdapter implements CreateLessonPort, ReadLessonPort, UpdateLessonPort {

    private final LessonPersistenceMapper lessonPersistenceMapper;
    private final LessonRepository lessonRepository;

    @Override
    public Lesson create(Lesson lesson) {
        return lessonPersistenceMapper.toDomain(lessonRepository.save(lessonPersistenceMapper.toEntity(lesson)));
    }

    @Override
    public Optional<Lesson> getLesson(Long no) {
        return lessonRepository.findByNo(no)
                .map(lessonPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Lesson> getLessonByOption(Long optionSeq) {
        return lessonRepository.findLessonByOptionSeq(optionSeq)
                .map(lessonPersistenceMapper::toDomain);
    }

    @Override
    public List<Lesson> getLessons(String profileId) {
        return lessonRepository.findByProfileId(profileId).stream()
                .map(lessonPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Lesson update(Lesson toBe) {
        return lessonPersistenceMapper.toDomain(lessonRepository.save(lessonPersistenceMapper.toEntity(toBe)));
    }
}
