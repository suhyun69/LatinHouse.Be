package com.latinhouse.backend.application.port.in.lesson.mapper;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.AddLessonCommand;
import com.latinhouse.backend.application.domain.lesson.service.UpdateLessonCommand;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LessonPortMapper {

    /*
        AppRequest -> Command
        Domain -> AppResponse
     */

    private final List<AppToCommandStrategy<?, ?>> appToCommandStrategies;
    private final List<DomainToAppStrategy<?, ?>> domainToAppStrategies;

    public AddLessonCommand toCommand(AddLessonAppRequest appReq) {
        return dispatchAppToCommand(appReq, AddLessonCommand.class);
    }

    public <A> A toAppRes(Lesson lesson, Class<A> appType) {
        return dispatchDomainToApp(lesson, appType);
    }

    public UpdateLessonCommand toCommand(UpdateLessonAppRequest appReq) {
        return dispatchAppToCommand(appReq, UpdateLessonCommand.class);
    }

    @SuppressWarnings("unchecked")
    private <A, C> C dispatchAppToCommand(A appReq, Class<C> commandType) {
        var s = (AppToCommandStrategy<A, C>) appToCommandStrategies.stream()
                .filter(st -> st.supports(appReq.getClass(), commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No appToCommandStrategy for %s -> %s".formatted(appReq.getClass().getSimpleName(), commandType.getSimpleName())));
        return s.toCommand(appReq);
    }

    @SuppressWarnings("unchecked")
    private <D, A> A dispatchDomainToApp(D domain, Class<A> appType) {
        var s = (DomainToAppStrategy<D, A>) domainToAppStrategies.stream()
                .filter(st -> st.supports(domain.getClass(), appType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No DomainToAppStrategy for %s -> %s".formatted(domain.getClass().getSimpleName(), appType.getSimpleName())));
        return s.toAppRes(domain);
    }
}
