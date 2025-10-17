package com.latinhouse.backend.adapter.in.web.home.mapper;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebRequest;
import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.adapter.in.web.home.dto.GetTodoWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppResponse;
import com.latinhouse.backend.port.in.home.dto.GetTodoAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HomeWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    public AddTodoAppRequest toAppReq(AddTodoWebRequest req) {
        return dispatchWebToApp(req, AddTodoAppRequest.class);
    }

    public AddTodoWebResponse toWebRes(AddTodoAppResponse res) {
        return dispatchAppToWeb(res, AddTodoWebResponse.class);
    }

    public GetTodoWebResponse toWebRes(GetTodoAppResponse res) {
        return dispatchAppToWeb(res, GetTodoWebResponse.class);
    }

    @SuppressWarnings("unchecked")
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
