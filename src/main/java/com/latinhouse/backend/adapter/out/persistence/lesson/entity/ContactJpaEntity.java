package com.latinhouse.backend.adapter.out.persistence.lesson.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lesson_contacts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lesson_no", nullable = false)
//    private LessonJpaEntity lesson;

    @Column(name = "lesson_no", insertable = false, updatable = false)
    private Long lessonNo; // 선택 사항

    @Column(length = 10)
    private String type;

    private String name;

    private String address;
}
