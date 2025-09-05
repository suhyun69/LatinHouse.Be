package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import com.latinhouse.backend.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "options")
@Data
@Builder // mapToJpaEntity
@NoArgsConstructor
@AllArgsConstructor
public class OptionT extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    // private Long lessonNo;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String region;
    private String location;
    private String locationUrl;
    private BigDecimal price;
}
