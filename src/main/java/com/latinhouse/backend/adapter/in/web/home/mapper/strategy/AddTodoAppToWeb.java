package com.latinhouse.backend.adapter.in.web.home.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.home.AddTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class AddTodoAppToWeb implements AppToWebStrategy<AddTodoAppResponse, AddTodoWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return AddTodoAppResponse.class.isAssignableFrom(a)
                && AddTodoWebResponse.class.isAssignableFrom(w);
    }

    @Override public AddTodoWebResponse toWebRes(AddTodoAppResponse appRes) {
        return AddTodoWebResponse.builder()
                .no(appRes.getNo())
                .build();
    }
}