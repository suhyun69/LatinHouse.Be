package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_options")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_no", nullable = false)
    private LessonJpaEntity lesson;

    @Column(nullable = false)
    private Long seq;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Column(length = 10)
    private String region;

    private String location;

    private String locationUrl;

    private BigDecimal price;
}
