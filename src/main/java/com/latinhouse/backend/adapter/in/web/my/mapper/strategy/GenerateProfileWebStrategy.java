package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppResponse;
import org.springframework.stereotype.Component;

@Component
public class GenerateProfileWebStrategy implements
        AppToWebStrategy<AddProfileAppResponse, GenerateProfileWebResponse>,
        WebToAppStrategy<GenerateProfileWebRequest, AddProfileAppRequest> {

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return AddProfileAppResponse.class.isAssignableFrom(a)
                && GenerateProfileWebResponse.class.isAssignableFrom(w);
    }

    @Override public GenerateProfileWebResponse toWebRes(AddProfileAppResponse appRes) {
        return GenerateProfileWebResponse.builder()
                .id(appRes.getId())
                .nickname(appRes.getNickname())
                .build();
    }

    @Override public boolean webToAppSupports(Class<?> w, Class<?> a) {
        return GenerateProfileWebRequest.class.isAssignableFrom(w)
                && AddProfileAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddProfileAppRequest toAppReq(GenerateProfileWebRequest webReq) {
        return AddProfileAppRequest.builder()
                .nickname(webReq.getNickname())
                .build();
    }
}