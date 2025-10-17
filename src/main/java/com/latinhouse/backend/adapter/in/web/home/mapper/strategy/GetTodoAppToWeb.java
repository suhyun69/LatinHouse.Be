package com.latinhouse.backend.adapter.in.web.home.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.adapter.in.web.home.dto.GetTodoWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.home.AddTodoAppResponse;
import com.latinhouse.backend.port.in.home.GetTodoAppResponse;
import org.springframework.stereotype.Component;

@Component
public class GetTodoAppToWeb implements AppToWebStrategy<GetTodoAppResponse, GetTodoWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return GetTodoAppResponse.class.isAssignableFrom(a)
                && GetTodoWebResponse.class.isAssignableFrom(w);
    }

    @Override public GetTodoWebResponse toWebRes(GetTodoAppResponse appRes) {
        return GetTodoWebResponse.builder()
                .no(appRes.getNo())
                .todo(appRes.getTodo())
                .isCompleted(appRes.getIsCompleted())
                .createdBy(appRes.getCreatedBy())
                .build();
    }
}