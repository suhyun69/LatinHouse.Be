package com.latinhouse.backend.adapter.in.web.checkout.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.PaymentWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppResponse;
import org.springframework.stereotype.Component;

@Component("Checkout.PaymentWebStrategy")
public class PaymentWebStrategy implements
        WebToAppStrategy<PaymentWebRequest, PaymentAppRequest>,
        AppToWebStrategy<PaymentAppResponse, PaymentWebResponse> {

    @Override
    public boolean webToAppSupports(Class<?> c, Class<?> d) {
        return PaymentWebRequest.class.isAssignableFrom(c)
                && PaymentAppRequest.class.isAssignableFrom(d);
    }

    @Override
    public PaymentAppRequest toAppReq(PaymentWebRequest webRequest) {
        return PaymentAppRequest.builder()
                .discounts(webRequest.getDiscounts())
                .build();
    }

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return PaymentAppResponse.class.isAssignableFrom(a)
                && PaymentWebResponse.class.isAssignableFrom(w);
    }

    @Override
    public PaymentWebResponse toWebRes(PaymentAppResponse res) {
        return PaymentWebResponse.builder().build();
    }

}
