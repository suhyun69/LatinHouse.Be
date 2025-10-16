package com.latinhouse.backend.application.addTodo.mapper;

import com.latinhouse.backend.application.signup.mapper.AppToCommandStrategy;
import com.latinhouse.backend.domain.todo.Todo;
import com.latinhouse.backend.domain.todo.command.AddTodoCommand;
import com.latinhouse.backend.port.in.dto.AddTodoAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddTodoAppMapper {

    /*
        AppRequest -> Command
        Domain -> AppResponse
     */

    private final List<AppToCommandStrategy<?, ?>> appToCommandStrategies;
    private final List<DomainToAppStrategy<?, ?>> domainToAppStrategies;

    public AddTodoCommand toCommand(AddTodoAppRequest appReq) {
        return dispatchAppToCommand(appReq, AddTodoCommand.class);
    }

    public <A> A toAppRes(Todo user, Class<A> appType) {
        return dispatchDomainToApp(user, appType);
    }

    @SuppressWarnings("unchecked")
    private <A, C> C dispatchAppToCommand(A appReq, Class<C> commandType) {
        var s = (AppToCommandStrategy<A, C>) appToCommandStrategies.stream()
                .filter(st -> st.supports(appReq.getClass(), commandType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No AppToCommandStrategy for %s -> %s".formatted(appReq.getClass().getSimpleName(), commandType.getSimpleName())));
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
