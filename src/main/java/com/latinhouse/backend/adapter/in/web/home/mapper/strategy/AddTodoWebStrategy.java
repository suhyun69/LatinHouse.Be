package com.latinhouse.backend.adapter.in.web.home.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebRequest;
import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppRequest;
import com.latinhouse.backend.port.in.home.dto.AddTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddTodoWebStrategy implements
        AppToWebStrategy<AddTodoAppResponse, AddTodoWebResponse>,
        WebToAppStrategy<AddTodoWebRequest, AddTodoAppRequest> {

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return AddTodoAppResponse.class.isAssignableFrom(a)
                && AddTodoWebResponse.class.isAssignableFrom(w);
    }

    @Override public AddTodoWebResponse toWebRes(AddTodoAppResponse appRes) {
        return AddTodoWebResponse.builder()
                .no(appRes.getNo())
                .build();
    }

    @Override public boolean webToAppSupports(Class<?> w, Class<?> a) {
        return AddTodoWebRequest.class.isAssignableFrom(w)
                && AddTodoAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddTodoAppRequest toAppReq(AddTodoWebRequest webReq) {
        return AddTodoAppRequest.builder()
                .todo(webReq.getTodo())
                .build();
    }
}