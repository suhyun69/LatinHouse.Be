package com.latinhouse.backend.application.lesson.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.lesson.Contact;
import com.latinhouse.backend.domain.lesson.Discount;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component("Lesson.GetLessonAppStrategy")
public class GetLessonAppStrategy implements DomainToAppStrategy<Lesson, GetLessonAppResponse> {

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Lesson.class.isAssignableFrom(c)
                && GetLessonAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public GetLessonAppResponse toAppRes(Lesson lesson) {
        return GetLessonAppResponse.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(lesson.getOptions().stream()
                        .map(this::toAppOption)
                        .collect(Collectors.toList()))
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts().stream()
                        .map(this::toAppDiscount)
                        .collect(Collectors.toList()))
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts().stream()
                        .map(this::toAppContact)
                        .collect(Collectors.toList()))
                .build();
    }

    private GetLessonAppResponse.Option toAppOption(Option option) {
        return GetLessonAppResponse.Option.builder()
                .startDateTime(option.getStartDateTime())
                .endDateTime(option.getEndDateTime())
                .region(option.getRegion())
                .location(option.getLocation())
                .locationUrl(option.getLocationUrl())
                .price(option.getPrice())
                .build();
    }

    private GetLessonAppResponse.Discount toAppDiscount(Discount discount) {
        return GetLessonAppResponse.Discount.builder()
                .type(discount.getType())
                .condition(discount.getCondition())
                .amount(discount.getAmount())
                .build();
    }

    private GetLessonAppResponse.Contact toAppContact(Contact contact) {
        return GetLessonAppResponse.Contact.builder()
                .type(contact.getType())
                .name(contact.getName())
                .address(contact.getAddress())
                .build();
    }
}
