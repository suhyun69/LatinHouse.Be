package com.latinhouse.backend.adapter.in.web.home.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.home.dto.DoneTodoWebRequest;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.home.dto.DoneTodoAppRequest;
import org.springframework.stereotype.Component;

@Component
public class DoneTodoWebStrategy implements WebToAppStrategy<DoneTodoWebRequest, DoneTodoAppRequest> {

    @Override public boolean webToAppSupports(Class<?> w, Class<?> a) {
        return DoneTodoWebRequest.class.isAssignableFrom(w)
                && DoneTodoAppRequest.class.isAssignableFrom(a);
    }

    @Override public DoneTodoAppRequest toAppReq(DoneTodoWebRequest webReq) {
        return DoneTodoAppRequest.builder()
                .no(webReq.getNo())
                .email(webReq.getEmail())
                .isAdmin(webReq.getIsAdmin())
                .build();
    }
}