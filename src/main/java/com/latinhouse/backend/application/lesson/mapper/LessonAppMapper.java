package com.latinhouse.backend.application.lesson.mapper;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonAppMapper {

    /*
        AppRequest -> Command
        Domain -> AppResponse
     */

    private final List<AppToCommandStrategy<?, ?>> appToCommandStrategies;
    private final List<DomainToAppStrategy<?, ?>> domainToAppStrategies;

    public <A> A toAppRes(Lesson lesson, Class<A> appType) {
        return dispatchDomainToApp(lesson, appType);
    }

    public <A> A toAppRes(Order order, Class<A> appType) {
        return dispatchDomainToApp(order, appType);
    }

    public AddOrderCommand toCommand(ApplyLessonAppRequest appReq) {
        return dispatchAppToCommand(appReq, AddOrderCommand.class);
    }

    private <A, C> C dispatchAppToCommand(A appReq, Class<C> commandType) {
        var s = (AppToCommandStrategy<A, C>) appToCommandStrategies.stream()
                .filter(st -> st.appToCommandSupports(appReq.getClass(), commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No AppToCommandStrategy for %s -> %s".formatted(appReq.getClass().getSimpleName(), commandType.getSimpleName())));
        return s.toCommand(appReq);
    }

    @SuppressWarnings("unchecked")
    private <D, A> A dispatchDomainToApp(D domain, Class<A> appType) {
        var s = (DomainToAppStrategy<D, A>) domainToAppStrategies.stream()
                .filter(st -> st.domainToAppSupports(domain.getClass(), appType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No DomainToAppStrategy for %s -> %s".formatted(domain.getClass().getSimpleName(), appType.getSimpleName())));
        return s.toAppRes(domain);
    }
}
