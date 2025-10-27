package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.SetProfileWebRequest;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.my.dto.SetProfileAppRequest;
import org.springframework.stereotype.Component;

@Component
public class SetProfileWebStrategy implements
        WebToAppStrategy<SetProfileWebRequest, SetProfileAppRequest> {

    @Override public boolean webToAppSupports(Class<?> a, Class<?> w) {
        return SetProfileWebRequest.class.isAssignableFrom(a)
                && SetProfileAppRequest.class.isAssignableFrom(w);
    }

    @Override public SetProfileAppRequest toAppReq(SetProfileWebRequest webReq) {
        return SetProfileAppRequest.builder()
                .profileId(webReq.getProfileId())
                .build();
    }
}
