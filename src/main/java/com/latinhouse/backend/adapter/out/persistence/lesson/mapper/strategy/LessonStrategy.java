package com.latinhouse.backend.adapter.out.persistence.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.out.persistence.lesson.entity.ContactJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.DiscountJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.LessonJpaEntity;
import com.latinhouse.backend.adapter.out.persistence.lesson.entity.OptionJpaEntity;
import com.latinhouse.backend.common.mapper.DomainToEntityStrategy;
import com.latinhouse.backend.common.mapper.EntityToDomainStrategy;
import com.latinhouse.backend.domain.lesson.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LessonStrategy implements
        DomainToEntityStrategy<Lesson, LessonJpaEntity>,
        EntityToDomainStrategy<LessonJpaEntity, Lesson> {

    @Override
    public boolean domainToEntitySupports(Class<?> c, Class<?> d) {
        return Lesson.class.isAssignableFrom(c)
                && LessonJpaEntity.class.isAssignableFrom(d);
    }

    @Override
    public LessonJpaEntity toEntity(Lesson lesson) {
        LessonJpaEntity entity = LessonJpaEntity.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre() != null ? lesson.getGenre().getCode() : null)
                .instructorLa(lesson.getInstructorLa())
                .instructorLo(lesson.getInstructorLo())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .build();

        // Options
        if (lesson.getOptions() != null) {
            entity.getOptions().addAll(
                    lesson.getOptions().stream()
                            .map(option -> OptionJpaEntity.builder()
                                    // .lesson(entity)
                                    .no(option.getNo())
                                    .startDateTime(option.getStartDateTime())
                                    .endDateTime(option.getEndDateTime())
                                    .region(option.getRegion() != null ? option.getRegion().getCode() : null)
                                    .location(option.getLocation())
                                    .locationUrl(option.getLocationUrl())
                                    .price(option.getPrice())
                                    .build())
                            .collect(Collectors.toList())
            );
        }

        // Discounts
        if (lesson.getDiscounts() != null) {
            entity.getDiscounts().addAll(
                    lesson.getDiscounts().stream()
                            .map(discount -> DiscountJpaEntity.builder()
                                    // .lesson(entity)
                                    .no(discount.getNo())
                                    .type(discount.getType() != null ? discount.getType().getCode() : null)
                                    .condition(discount.getCondition())
                                    .amount(discount.getAmount())
                                    .build())
                            .collect(Collectors.toList())
            );
        }

        // Contacts
        if (lesson.getContacts() != null) {
            entity.getContacts().addAll(
                    lesson.getContacts().stream()
                            .map(contact -> ContactJpaEntity.builder()
                                    // .lesson(entity)
                                    .no(contact.getNo())
                                    .type(contact.getType() != null ? contact.getType().getCode() : null)
                                    .name(contact.getName())
                                    .address(contact.getAddress())
                                    .build())
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }

    @Override
    public boolean entityToDomainSupports(Class<?> c, Class<?> d) {
        return LessonJpaEntity.class.isAssignableFrom(c)
                && Lesson.class.isAssignableFrom(d);
    }

    @Override
    public Lesson toDomain(LessonJpaEntity entity) {
        return Lesson.builder()
                .no(entity.getNo())
                .title(entity.getTitle())
                .genre(entity.getGenre() != null ? Genre.of(entity.getGenre()) : null)
                .instructorLa(entity.getInstructorLa())
                .instructorLo(entity.getInstructorLo())
                .options(entity.getOptions() != null ?
                        entity.getOptions().stream()
                                .map(option -> Option.builder()
                                        .no(option.getNo())
                                        .startDateTime(option.getStartDateTime())
                                        .endDateTime(option.getEndDateTime())
                                        .region(option.getRegion() != null ? Region.of(option.getRegion()) : null)
                                        .location(option.getLocation())
                                        .locationUrl(option.getLocationUrl())
                                        .price(option.getPrice())
                                        .build())
                                .collect(Collectors.toList()) : null)
                .bank(entity.getBank())
                .accountNumber(entity.getAccountNumber())
                .accountOwner(entity.getAccountOwner())
                .discounts(entity.getDiscounts() != null ?
                        entity.getDiscounts().stream()
                                .map(discount -> Discount.builder()
                                        .no(discount.getNo())
                                        .type(discount.getType() != null ? DiscountType.of(discount.getType()) : null)
                                        .condition(discount.getCondition())
                                        .amount(discount.getAmount())
                                        .build())
                                .collect(Collectors.toList()) : null)
                .maxDiscountAmount(entity.getMaxDiscountAmount())
                .contacts(entity.getContacts() != null ?
                        entity.getContacts().stream()
                                .map(contact -> Contact.builder()
                                        .no(contact.getNo())
                                        .type(contact.getType() != null ? ContactType.of(contact.getType()) : null)
                                        .name(contact.getName())
                                        .address(contact.getAddress())
                                        .build())
                                .collect(Collectors.toList()) : null)
                .build();
    }
}
