package sample.application.mapper.strategy;

import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import org.springframework.stereotype.Component;
import sample.domain.Sample;
import sample.port.in.dto.GetSampleAppResponse;

@Component("Sample.GetSampleAppStrategy")
public class GetSampleAppStrategy implements DomainToAppStrategy<Sample, GetSampleAppResponse>  {

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Sample.class.isAssignableFrom(c)
                && GetSampleAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public GetSampleAppResponse toAppRes(Sample sample) {
        return GetSampleAppResponse.builder()
                .id(sample.getId())
                .build();
    }
}
