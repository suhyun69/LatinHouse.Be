package com.latinhouse.backend.adapter.out.persistence.lesson.repository;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<LessonJpaEntity, Long> {
    Optional<LessonJpaEntity> findByNo(Long no);
}
