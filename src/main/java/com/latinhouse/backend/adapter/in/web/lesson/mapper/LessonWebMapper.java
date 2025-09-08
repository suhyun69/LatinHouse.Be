package com.latinhouse.backend.adapter.in.web.lesson.mapper;

import com.latinhouse.backend.adapter.in.web.lesson.dto.*;
import com.latinhouse.backend.application.port.in.lesson.dto.*;
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

    public AddLessonAppRequest toAppReq(AddLessonWebRequest req) {
        return dispatchWebToApp(req, AddLessonAppRequest.class);
    }

    public AddLessonWebResponse toWebRes(AddLessonAppResponse res) {
        return dispatchAppToWeb(res, AddLessonWebResponse.class);
    }

    public LessonWebResponse toWebRes(LessonAppResponse res) {
        return dispatchAppToWeb(res, LessonWebResponse.class);
    }

    public UpdateLessonAppRequest toAppReq(Long lessonNo, UpdateLessonWebRequest webReq) {
        webReq.setNo(lessonNo);
        return dispatchWebToApp(webReq, UpdateLessonAppRequest.class);
    }

    public UpdateLessonWebResponse toWebRes(UpdateLessonAppResponse appRes) {
        return dispatchAppToWeb(appRes, UpdateLessonWebResponse.class);
    }

    @SuppressWarnings("unchecked")
    private <W, A> A dispatchWebToApp(W webReq, Class<A> appType) {
        var s = (WebToAppStrategy<W, A>) webToAppStrategies.stream()
                .filter(st -> st.supports(webReq.getClass(), appType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No WebToAppStrategy for %s -> %s".formatted(webReq.getClass().getSimpleName(), appType.getSimpleName())));
        return s.toAppReq(webReq);
    }

    @SuppressWarnings("unchecked")
    private <A, W> W dispatchAppToWeb(A appRes, Class<W> webType) {
        var s = (AppToWebStrategy<A, W>) appToWebStrategies.stream()
                .filter(st -> st.supports(appRes.getClass(), webType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No AppToWebStrategy for %s -> %s".formatted(appRes.getClass().getSimpleName(), webType.getSimpleName())));
        return s.toWebRes(appRes);
    }
}
