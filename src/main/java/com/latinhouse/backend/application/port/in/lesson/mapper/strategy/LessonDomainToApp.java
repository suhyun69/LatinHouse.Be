package com.latinhouse.backend.application.port.in.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.Contact;
import com.latinhouse.backend.application.domain.lesson.Discount;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.Option;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.DomainToAppStrategy;
import org.springframework.stereotype.Component;

@Component
public class LessonDomainToApp implements DomainToAppStrategy<Lesson, LessonAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
        return Lesson.class.isAssignableFrom(d)
                && LessonAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public LessonAppResponse toAppRes(Lesson lesson) {
        return LessonAppResponse.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(lesson.getOptions().stream().map(LessonDomainToApp::convertTo).toList())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts().stream().map(LessonDomainToApp::convertTo).toList())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts().stream().map(LessonDomainToApp::convertTo).toList())
                .build();
    }

    private static LessonAppResponse.Option convertTo(Option o) {
        return LessonAppResponse.Option.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static LessonAppResponse.Discount convertTo(Discount d) {
        return LessonAppResponse.Discount.builder()
                .seq(d.getSeq())
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static LessonAppResponse.Contact convertTo(Contact c) {
        return LessonAppResponse.Contact.builder()
                .seq(c.getSeq())
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
