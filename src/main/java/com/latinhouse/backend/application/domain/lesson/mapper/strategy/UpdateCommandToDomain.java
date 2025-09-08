package com.latinhouse.backend.application.domain.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.Contact;
import com.latinhouse.backend.application.domain.lesson.Discount;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.Option;
import com.latinhouse.backend.application.domain.lesson.mapper.CommandToDomainStrategy;
import com.latinhouse.backend.application.domain.lesson.service.UpdateLessonCommand;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommandToDomain  implements CommandToDomainStrategy<UpdateLessonCommand, Lesson> {

    @Override
    public boolean supports(Class<?> c, Class<?> d) {
        return UpdateLessonCommand.class.isAssignableFrom(c)
                && Lesson.class.isAssignableFrom(d);
    }

    @Override
    public Lesson toDomain(UpdateLessonCommand cmd) {
        return Lesson.builder()
                .no(cmd.getNo())
                .title(cmd.getTitle())
                .genre(cmd.getGenre())
                .instructorLo(cmd.getInstructorLo())
                .instructorLa(cmd.getInstructorLa())
                .options(cmd.getOptions().stream().map(UpdateCommandToDomain::convertTo).toList())
                .bank(cmd.getBank())
                .accountNumber(cmd.getAccountNumber())
                .accountOwner(cmd.getAccountOwner())
                .discounts(cmd.getDiscounts().stream().map(UpdateCommandToDomain::convertTo).toList())
                .maxDiscountAmount(cmd.getMaxDiscountAmount())
                .contacts(cmd.getContacts().stream().map(UpdateCommandToDomain::convertTo).toList())
                .build();
    }

    private static Option convertTo(UpdateLessonCommand.Option o) {
        return Option.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static Discount convertTo(UpdateLessonCommand.Discount d) {
        return Discount.builder()
                .seq(d.getSeq())
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static Contact convertTo(UpdateLessonCommand.Contact c) {
        return Contact.builder()
                .seq(c.getSeq())
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
