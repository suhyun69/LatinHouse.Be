package com.latinhouse.backend.adapter.out.persistence.lesson.repository;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<LessonJpaEntity, Long> {
    Optional<LessonJpaEntity> findByNo(Long no);

    @Query("SELECT l FROM LessonJpaEntity l WHERE l.instructorLo = :profileId OR l.instructorLa = :profileId")
    List<LessonJpaEntity> findByProfileId(@Param("profileId") String profileId);

    @Query("""
        SELECT l
        FROM LessonJpaEntity l
            JOIN l.options o
        WHERE o.seq = :optionSeq
    """)
    Optional<LessonJpaEntity> findLessonByOptionSeq(@Param("optionSeq") Long optionSeq);
}
