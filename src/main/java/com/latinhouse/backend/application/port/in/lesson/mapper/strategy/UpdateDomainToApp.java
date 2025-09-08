package com.latinhouse.backend.application.port.in.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.DomainToAppStrategy;
import org.springframework.stereotype.Component;

@Component
public class UpdateDomainToApp implements DomainToAppStrategy<Lesson, UpdateLessonAppResponse> {

    @Override
    public boolean supports(Class<?> d, Class<?> a) {
        return Lesson.class.isAssignableFrom(d)
                && UpdateLessonAppResponse.class.isAssignableFrom(a);
    }

    @Override
    public UpdateLessonAppResponse toAppRes(Lesson lesson) {
        return UpdateLessonAppResponse.builder()
                .no(lesson.getNo())
                .build();
    }
}
