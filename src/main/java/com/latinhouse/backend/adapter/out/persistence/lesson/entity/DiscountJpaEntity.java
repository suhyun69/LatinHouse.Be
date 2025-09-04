package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import com.latinhouse.backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discounts")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class DiscountJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    // private Long lessonNo;

    private String type;
    private String condition;
    private Integer amount;
}
