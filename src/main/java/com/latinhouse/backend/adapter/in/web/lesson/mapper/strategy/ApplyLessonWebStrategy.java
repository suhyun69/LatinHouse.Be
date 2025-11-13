package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
public class ApplyLessonWebStrategy implements
        WebToAppStrategy<ApplyLessonWebRequest, ApplyLessonAppRequest>,
        AppToWebStrategy<ApplyLessonAppResponse, ApplyLessonWebResponse> {

    @Override
    public boolean webToAppSupports(Class<?> c, Class<?> d) {
        return ApplyLessonWebRequest.class.isAssignableFrom(c)
                && ApplyLessonAppRequest.class.isAssignableFrom(d);
    }

    @Override
    public ApplyLessonAppRequest toAppReq(ApplyLessonWebRequest webReq) {
        return ApplyLessonAppRequest.builder()
                .lessonOptionNo(webReq.getLessonOptionNo())
                .build();
    }

    @Override
    public boolean appToWebSupports(Class<?> c, Class<?> d) {
        return ApplyLessonAppResponse.class.isAssignableFrom(c)
                && ApplyLessonWebResponse.class.isAssignableFrom(d);
    }

    @Override
    public ApplyLessonWebResponse toWebRes(ApplyLessonAppResponse appRes) {
        return ApplyLessonWebResponse.builder()
                .checkoutId(appRes.getCheckoutId())
                .build();
    }
}
