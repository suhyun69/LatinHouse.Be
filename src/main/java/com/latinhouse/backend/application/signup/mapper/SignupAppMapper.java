package com.latinhouse.backend.application.signup.mapper;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.domain.user.command.AddUserCommand;
import com.latinhouse.backend.port.in.signup.dto.AddUserAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SignupAppMapper {

    /*
        AppRequest -> Command
        Domain -> AppResponse
     */

    private final List<AppToCommandStrategy<?, ?>> appToCommandStrategies;
    private final List<DomainToAppStrategy<?, ?>> domainToAppStrategies;

    public AddUserCommand toCommand(AddUserAppRequest appReq) {
        return dispatchAppToCommand(appReq, AddUserCommand.class);
    }

    public <A> A toAppRes(User user, Class<A> appType) {
        return dispatchDomainToApp(user, appType);
    }

    @SuppressWarnings("unchecked")
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
