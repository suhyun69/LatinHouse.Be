package com.latinhouse.backend.adapter.in.web.signup.mapper;

import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.port.in.dto.AddUserAppRequest;
import com.latinhouse.backend.port.in.dto.AddUserAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SignupWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    public AddUserAppRequest toAppReq(SignupWebRequest req) {
        return dispatchWebToApp(req, AddUserAppRequest.class);
    }

    public SignupWebResponse toWebRes(AddUserAppResponse res) {
        return dispatchAppToWeb(res, SignupWebResponse.class);
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
