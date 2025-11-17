package sample.adapter.in.web.mapper;

import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.adapter.in.web.dto.GetSampleWebRequest;
import sample.adapter.in.web.dto.GetSampleWebResponse;
import sample.port.in.dto.GetSampleAppRequest;
import sample.port.in.dto.GetSampleAppResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    /* start of GetSample */
    public GetSampleAppRequest toAppReq(GetSampleWebRequest webReq) {
        return dispatchWebToApp(webReq, GetSampleAppRequest.class);
    }

    public GetSampleWebResponse toWebRes(GetSampleAppResponse res) {
        return dispatchAppToWeb(res, GetSampleWebResponse.class);
    }
    /* end of GetSample */
    
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
