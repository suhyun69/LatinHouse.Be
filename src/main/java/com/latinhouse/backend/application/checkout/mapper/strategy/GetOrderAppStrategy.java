package com.latinhouse.backend.application.checkout.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

@Component("Checkout.GetOrderAppStrategy")
public class GetOrderAppStrategy implements DomainToAppStrategy<Order, GetCheckoutAppResponse> {

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Order.class.isAssignableFrom(c)
                && GetCheckoutAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public GetCheckoutAppResponse toAppRes(Order order) {
        return GetCheckoutAppResponse.builder()
                .checkoutId(order.getId())
                .status(order.getStatus())
                .build();
    }
}
