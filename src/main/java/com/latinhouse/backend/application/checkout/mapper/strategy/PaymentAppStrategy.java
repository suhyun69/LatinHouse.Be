package com.latinhouse.backend.application.checkout.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.port.in.checkout.dto.PaymentAppResponse;
import org.springframework.stereotype.Component;

@Component("Checkout.PaymentAppStrategy")
public class PaymentAppStrategy  implements DomainToAppStrategy<Order, PaymentAppResponse> {

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Order.class.isAssignableFrom(c)
                && PaymentAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public PaymentAppResponse toAppRes(Order order) {
        return PaymentAppResponse.builder()
                .build();
    }
}
