package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.GetLessonWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.port.in.my.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

@Component
public class GetLessonWebStrategy implements AppToWebStrategy<GetLessonAppResponse, GetLessonWebResponse> {

    @Override
    public boolean appToWebSupports(Class<?> c, Class<?> d) {
        return GetLessonAppResponse.class.isAssignableFrom(c)
                && GetLessonWebResponse.class.isAssignableFrom(d);
    }

    @Override
    public GetLessonWebResponse toWebRes(GetLessonAppResponse appRes) {
        return GetLessonWebResponse.builder()
                .no(appRes.getNo())
                .title(appRes.getTitle())
                .genre(appRes.getGenre())
                .instructorLa(appRes.getInstructorLa())
                .instructorLo(appRes.getInstructorLo())
                .options(appRes.getOptions())
                .bank(appRes.getBank())
                .accountNumber(appRes.getAccountNumber())
                .accountOwner(appRes.getAccountOwner())
                .discounts(appRes.getDiscounts())
                .maxDiscountAmount(appRes.getMaxDiscountAmount())
                .contacts(appRes.getContacts())
                .build();
    }
}
