package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import com.latinhouse.backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private String title;

    @Column(length = 10)
    private String genre;

    private String instructorLa;

    private String instructorLo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false) // FK는 자식 테이블에 생성/관리
    @Builder.Default
    private List<OptionJpaEntity> options = new ArrayList<>();

    private String bank;

    private String accountNumber;

    private String accountOwner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false) // FK는 자식 테이블에 생성/관리
    @Builder.Default
    private List<DiscountJpaEntity> discounts = new ArrayList<>();

    private BigDecimal maxDiscountAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false) // FK는 자식 테이블에 생성/관리
    @Builder.Default
    private List<ContactJpaEntity> contacts = new ArrayList<>();
}
