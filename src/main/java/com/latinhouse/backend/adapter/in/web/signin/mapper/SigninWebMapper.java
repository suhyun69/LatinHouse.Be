package com.latinhouse.backend.adapter.in.web.signin.mapper;

import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebRequest;
import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.signin.SigninAppRequest;
import com.latinhouse.backend.port.in.signin.SigninAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SigninWebMapper {

    /*
        WebRequest -> AppRequest
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    public SigninAppRequest toAppReq(SigninWebRequest req) {
        return dispatchWebToApp(req, SigninAppRequest.class);
    }

    public SigninWebResponse toWebRes(SigninAppResponse res) {
        return dispatchAppToWeb(res, SigninWebResponse.class);
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
