package com.latinhouse.backend.adapter.in.web.checkout.mapper;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckoutWebMapper {

    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */

    private final List<WebToAppStrategy<?, ?>> webToAppStrategies;
    private final List<AppToWebStrategy<?, ?>> appToWebStrategies;

    /* start of GetCheckout */
    public GetCheckoutAppRequest toAppReq(GetCheckoutWebRequest webReq) {
        return dispatchWebToApp(webReq, GetCheckoutAppRequest.class);
    }

    public GetCheckoutWebResponse toWebRes(GetCheckoutAppResponse res) {
        return dispatchAppToWeb(res, GetCheckoutWebResponse.class);
    }
    /* end of GetCheckout */

    /* start of Payment */
    public PaymentAppRequest toAppReq(PaymentWebRequest webReq) {
        return dispatchWebToApp(webReq, PaymentAppRequest.class);
    }

    public PaymentWebResponse toWebRes(PaymentAppResponse res) {
        return dispatchAppToWeb(res, PaymentWebResponse.class);
    }
    /* end of Payment */

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
