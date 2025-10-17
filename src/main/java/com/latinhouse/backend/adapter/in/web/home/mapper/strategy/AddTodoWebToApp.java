package com.latinhouse.backend.adapter.in.web.home.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebRequest;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.dto.AddTodoAppRequest;
import org.springframework.stereotype.Component;

@Component
public class AddTodoWebToApp implements WebToAppStrategy<AddTodoWebRequest, AddTodoAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
        return AddTodoWebRequest.class.isAssignableFrom(w)
                && AddTodoAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddTodoAppRequest toAppReq(AddTodoWebRequest webReq) {
        return AddTodoAppRequest.builder()
                .todo(webReq.getTodo())
                .build();
    }
}