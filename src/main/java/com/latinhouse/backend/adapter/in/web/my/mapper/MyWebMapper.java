package com.latinhouse.backend.adapter.in.web.my.mapper;

import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.my.dto.GetProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.my.dto.SetProfileWebRequest;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import com.latinhouse.backend.port.in.my.dto.GetProfileAppResponse;
import com.latinhouse.backend.port.in.my.dto.SetProfileAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    public AddProfileAppRequest toAppReq(GenerateProfileWebRequest req) {
        return dispatchWebToApp(req, AddProfileAppRequest.class);
    }

    public GenerateProfileWebResponse toWebRes(AddProfileAppResponse res) {
        return dispatchAppToWeb(res, GenerateProfileWebResponse.class);
    }

    public GetProfileWebResponse toWebRes(GetProfileAppResponse res) {
        return dispatchAppToWeb(res, GetProfileWebResponse.class);
    }

    public SetProfileAppRequest toAppReq(SetProfileWebRequest req) {
        return dispatchWebToApp(req, SetProfileAppRequest.class);
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
