package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.UpdateLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.AppToWebStrategy;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateAppToWeb implements AppToWebStrategy<UpdateLessonAppResponse, UpdateLessonWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return UpdateLessonAppResponse.class.isAssignableFrom(a)
                && UpdateLessonWebResponse.class.isAssignableFrom(w);
    }

    @Override public UpdateLessonWebResponse toWebRes(UpdateLessonAppResponse appRes) {
        return UpdateLessonWebResponse.builder()
                .no(appRes.getNo())
                .build();
    }
}
