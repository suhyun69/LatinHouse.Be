package com.latinhouse.backend.application.lesson.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
public class ApplyLessonAppStrategy implements
        AppToCommandStrategy<ApplyLessonAppRequest, AddOrderCommand>,
        DomainToAppStrategy<Order, ApplyLessonAppResponse> {

    @Override
    public boolean appToCommandSupports(Class<?> c, Class<?> d) {
        return ApplyLessonAppRequest.class.isAssignableFrom(c)
                && AddOrderCommand.class.isAssignableFrom(d);
    }

    @Override
    public AddOrderCommand toCommand(ApplyLessonAppRequest appReq) {
        return AddOrderCommand.builder()
                .lessonOptionSeq(appReq.getLessonOptionSeq())
                .build();
    }

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Order.class.isAssignableFrom(c)
                && ApplyLessonAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public ApplyLessonAppResponse toAppRes(Order order) {
        return ApplyLessonAppResponse.builder()
                .checkoutId(order.getId())
                .build();
    }
}
