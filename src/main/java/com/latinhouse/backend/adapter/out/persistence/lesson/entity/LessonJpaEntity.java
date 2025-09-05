package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import com.latinhouse.backend.common.BaseEntity;
import com.latinhouse.backend.domain.lesson.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "lessons")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class LessonJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String title;
    private String genre;
    private String instructorLo;
    private String instructorLa;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false)
    private List<OptionJpaEntity> options =  new ArrayList<>();

    private String bank;
    private String accountNumber;
    private String accountOwner;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false)
    private List<DiscountJpaEntity> discounts = new ArrayList<>();
    private BigDecimal maxDiscountAmount;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_no", nullable = false)
    private List<ContactJpaEntity> contacts = new ArrayList<>();
}
