package com.latinhouse.backend.application.port.in.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.DomainToAppStrategy;
import org.springframework.stereotype.Component;

@Component
public class AddDomainToApp implements DomainToAppStrategy<Lesson, AddLessonAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
        return Lesson.class.isAssignableFrom(d)
                && AddLessonAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public AddLessonAppResponse toAppRes(Lesson lesson) {
        return AddLessonAppResponse.builder()
                .no(lesson.getNo())
                .build();
    }
}
