package com.latinhouse.backend.adapter.out.persistence.lesson.mapper;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.ContactT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.DiscountT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonT;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.OptionT;
import com.latinhouse.backend.application.domain.lesson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component("lessonPersistenceMapper")
@RequiredArgsConstructor
public class LessonPersistenceMapper {
    public LessonT toEntity(Lesson lesson) {
        return LessonT.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre().getCode())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(lesson.getOptions().stream().map(LessonPersistenceMapper::convertTo).toList())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts().stream().map(LessonPersistenceMapper::convertTo).toList())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts().stream().map(LessonPersistenceMapper::convertTo).toList())
                .isActive(lesson.getIsActive())
                .build();
    }

    private static OptionT convertTo(Option o) {
        return OptionT.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion().getCode())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .isActive(o.getIsActive())
                .build();
    }

    private static DiscountT convertTo(Discount d) {
        return DiscountT.builder()
                .seq(d.getSeq())
                .type(d.getType().getCode())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .isActive(d.getIsActive())
                .build();
    }

    private static ContactT convertTo(Contact c) {
        return ContactT.builder()
                .seq(c.getSeq())
                .type(c.getType().getCode())
                .name(c.getName())
                .address(c.getAddress())
                .isActive(c.getIsActive())
                .build();
    }

    public Lesson toDomain(LessonT lessonT) {
        return Lesson.builder()
                .no(lessonT.getNo())
                .title(lessonT.getTitle())
                .genre(Genre.of(lessonT.getGenre()))
                .instructorLo(lessonT.getInstructorLo())
                .instructorLa(lessonT.getInstructorLa())
                .options(lessonT.getOptions().stream().map(LessonPersistenceMapper::convertTo).collect(Collectors.toCollection(ArrayList::new)))
                .bank(lessonT.getBank())
                .accountNumber(lessonT.getAccountNumber())
                .accountOwner(lessonT.getAccountOwner())
                .discounts(lessonT.getDiscounts().stream().map(LessonPersistenceMapper::convertTo).collect(Collectors.toCollection(ArrayList::new)))
                .maxDiscountAmount(lessonT.getMaxDiscountAmount())
                .contacts(lessonT.getContacts().stream().map(LessonPersistenceMapper::convertTo).collect(Collectors.toCollection(ArrayList::new)))
                .isActive(lessonT.getIsActive())
                .build();
    }

    private static Option convertTo(OptionT o) {
        return Option.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(Region.of(o.getRegion()))
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .isActive(o.getIsActive())
                .build();
    }

    private static Discount convertTo(DiscountT d) {
        return Discount.builder()
                .seq(d.getSeq())
                .type(DiscountType.of(d.getType()))
                .condition(d.getCondition())
                .amount(d.getAmount())
                .isActive(d.getIsActive())
                .build();
    }

    private static Contact convertTo(ContactT c) {
        return Contact.builder()
                .seq(c.getSeq())
                .type(ContactType.of(c.getType()))
                .name(c.getName())
                .address(c.getAddress())
                .isActive(c.getIsActive())
                .build();
    }
}
