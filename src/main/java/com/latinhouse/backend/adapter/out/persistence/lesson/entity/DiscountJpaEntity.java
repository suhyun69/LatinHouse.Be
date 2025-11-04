package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "lesson_discounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lesson_no", nullable = false)
//    private LessonJpaEntity lesson;

    @Column(name = "lesson_no", insertable = false, updatable = false)
    private Long lessonNo; // 선택 사항

    @Column(length = 10)
    private String type;

    @Column(name = "discount_condition")
    private String condition;

    private BigDecimal amount;
}
