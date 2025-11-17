package sample.application.mapper;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.domain.Sample;
import sample.domain.command.AddSampleCommand;
import sample.port.in.dto.GetSampleAppRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleAppMapper {

    /*
        AppRequest -> Command
        Domain -> AppResponse
     */

    private final List<AppToCommandStrategy<?, ?>> appToCommandStrategies;
    private final List<DomainToAppStrategy<?, ?>> domainToAppStrategies;

    public AddSampleCommand toCommand(GetSampleAppRequest appReq) {
        return dispatchAppToCommand(appReq, AddSampleCommand.class);
    }

    public <A> A toAppRes(Sample sample, Class<A> appType) {
        return dispatchDomainToApp(sample, appType);
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
