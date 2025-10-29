package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.GetProfileWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.my.dto.GetProfileAppResponse;
import org.springframework.stereotype.Component;

@Component
public class GetProfilesWebStrategy implements
        AppToWebStrategy<GetProfileAppResponse, GetProfileWebResponse> {

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return GetProfileAppResponse.class.isAssignableFrom(a)
                && GetProfileWebResponse.class.isAssignableFrom(w);
    }

    @Override public GetProfileWebResponse toWebRes(GetProfileAppResponse appRes) {
        return GetProfileWebResponse.builder()
                .id(appRes.getId())
                .nickname(appRes.getNickname())
                .sex(appRes.getSex().getCode())
                .isInstructor(appRes.getIsInstructor())
                .build();
    }
}
