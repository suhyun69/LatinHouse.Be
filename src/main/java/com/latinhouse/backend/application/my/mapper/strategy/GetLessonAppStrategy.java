package com.latinhouse.backend.application.my.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.port.in.my.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
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
                .instructorLa(lesson.getInstructorLa())
                .instructorLo(lesson.getInstructorLo())
                .options(lesson.getOptions())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts())
                .build();
    }
}
