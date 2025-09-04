package com.latinhouse.backend.adapter.out.persistence.lesson.repository;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository  extends JpaRepository<LessonJpaEntity, Long> {
}
