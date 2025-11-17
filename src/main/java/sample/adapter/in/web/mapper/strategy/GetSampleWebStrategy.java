package sample.adapter.in.web.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import org.springframework.stereotype.Component;
import sample.adapter.in.web.dto.GetSampleWebRequest;
import sample.adapter.in.web.dto.GetSampleWebResponse;
import sample.port.in.dto.GetSampleAppRequest;
import sample.port.in.dto.GetSampleAppResponse;

@Component("Sample.GetSampleWebStrategy")
public class GetSampleWebStrategy implements
        WebToAppStrategy<GetSampleWebRequest, GetSampleAppRequest>,
        AppToWebStrategy<GetSampleAppResponse, GetSampleWebResponse> {

    @Override
    public boolean webToAppSupports(Class<?> c, Class<?> d) {
        return GetSampleWebRequest.class.isAssignableFrom(c)
                && GetSampleAppRequest.class.isAssignableFrom(d);
    }

    @Override
    public GetSampleAppRequest toAppReq(GetSampleWebRequest webReq) {
        return GetSampleAppRequest.builder()
                .id(webReq.getId())
                .build();
    }

    @Override
    public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return GetSampleAppResponse.class.isAssignableFrom(a)
                && GetSampleWebResponse.class.isAssignableFrom(w);
    }

    @Override
    public GetSampleWebResponse toWebRes(GetSampleAppResponse appRes) {
        return GetSampleWebResponse.builder()
                .id(appRes.getId())
                .build();
    }
}
