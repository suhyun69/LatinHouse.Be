package com.latinhouse.backend.adapter.in.web.lesson.mapper;

import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.dto.GetLessonWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    public GetLessonWebResponse toWebRes(GetLessonAppResponse res) {
        return dispatchAppToWeb(res, GetLessonWebResponse.class);
    }

    /* start of ApplyLesson */

    public ApplyLessonAppRequest toAppReq(ApplyLessonWebRequest webReq) {
        return dispatchWebToApp(webReq, ApplyLessonAppRequest.class);
    }

    public ApplyLessonWebResponse toWebRes(ApplyLessonAppResponse res) {
        return dispatchAppToWeb(res, ApplyLessonWebResponse.class);
    }

    /* end of ApplyLesson */

    private <W, A> A dispatchWebToApp(W webReq, Class<A> appType) {
        var s = (WebToAppStrategy<W, A>) webToAppStrategies.stream()
                .filter(st -> st.webToAppSupports(webReq.getClass(), appType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No WebToAppStrategy for %s -> %s".formatted(webReq.getClass().getSimpleName(), appType.getSimpleName())));
        return s.toAppReq(webReq);
    }

    @SuppressWarnings("unchecked")
    private <A, W> W dispatchAppToWeb(A appRes, Class<W> webType) {
        var s = (AppToWebStrategy<A, W>) appToWebStrategies.stream()
                .filter(st -> st.appToWebSupports(appRes.getClass(), webType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No AppToWebStrategy for %s -> %s".formatted(appRes.getClass().getSimpleName(), webType.getSimpleName())));
        return s.toWebRes(appRes);
    }
}
