package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.AppToWebStrategy;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddAppToWeb implements AppToWebStrategy<AddLessonAppResponse, AddLessonWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return AddLessonAppResponse.class.isAssignableFrom(a)
                && AddLessonWebResponse.class.isAssignableFrom(w);
    }

    @Override public AddLessonWebResponse toWebRes(AddLessonAppResponse appRes) {
        return AddLessonWebResponse.builder()
                .no(appRes.getNo())
                .build();
    }
}