package com.latinhouse.backend.domain.lesson.mapper;

import com.latinhouse.backend.domain.lesson.Contact;
import com.latinhouse.backend.domain.lesson.Discount;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.domain.lesson.service.AddLessonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("lessonDomainMapper")
@RequiredArgsConstructor
public class LessonMapper {

    public Lesson toDomain(AddLessonCommand cmd) {
        return Lesson.builder()
                .title(cmd.getTitle())
                .genre(cmd.getGenre())
                .instructorLo(cmd.getInstructorLo())
                .instructorLa(cmd.getInstructorLa())
                .options(cmd.getOptions().stream().map(LessonMapper::convertTo).toList())
                .bank(cmd.getBank())
                .accountNumber(cmd.getAccountNumber())
                .accountOwner(cmd.getAccountOwner())
                .discounts(cmd.getDiscounts().stream().map(LessonMapper::convertTo).toList())
                .maxDiscountAmount(cmd.getMaxDiscountAmount())
                .contacts(cmd.getContacts().stream().map(LessonMapper::convertTo).toList())
                .build();
    }

    private static Option convertTo(AddLessonCommand.Option o) {
        return Option.builder()
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static Discount convertTo(AddLessonCommand.Discount d) {
        return Discount.builder()
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static Contact convertTo(AddLessonCommand.Contact c) {
        return Contact.builder()
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
