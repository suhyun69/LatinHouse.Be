package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.AppToWebStrategy;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.util.DateTimeUtil;
import org.springframework.stereotype.Component;

@Component
public class LessonAppToWeb implements AppToWebStrategy<LessonAppResponse, LessonWebResponse> {

    @Override public boolean supports(Class<?> a, Class<?> w) {
        return LessonAppResponse.class.isAssignableFrom(a)
                && LessonWebResponse.class.isAssignableFrom(w);
    }

    @Override public LessonWebResponse toWebRes(LessonAppResponse appRes) {
        return LessonWebResponse.builder()
                .no(appRes.getNo())
                .title(appRes.getTitle())
                .genre(appRes.getGenre().getCode())
                .instructorLo(appRes.getInstructorLo())
                .instructorLa(appRes.getInstructorLa())
                .options(appRes.getOptions().stream().map(LessonAppToWeb::convertTo).toList())
                .bank(appRes.getBank())
                .accountNumber(appRes.getAccountNumber())
                .accountOwner(appRes.getAccountOwner())
                .discounts(appRes.getDiscounts().stream().map(LessonAppToWeb::convertTo).toList())
                .maxDiscountAmount(appRes.getMaxDiscountAmount())
                .contacts(appRes.getContacts().stream().map(LessonAppToWeb::convertTo).toList())
                .build();
    }

    private static LessonWebResponse.Option convertTo(LessonAppResponse.Option o) {
        return LessonWebResponse.Option.builder()
                .seq(o.getSeq())
                .startDate(DateTimeUtil.toDateString(o.getStartDateTime()))
                .endDate(DateTimeUtil.toDateString(o.getEndDateTime()))
                .startTime(DateTimeUtil.toTimeString(o.getStartDateTime()))
                .endTime(DateTimeUtil.toTimeString(o.getEndDateTime()))
                .region(o.getRegion().getCode())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static LessonWebResponse.Discount convertTo(LessonAppResponse.Discount d) {
        return LessonWebResponse.Discount.builder()
                .seq(d.getSeq())
                .type(d.getType().getCode())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static LessonWebResponse.Contact convertTo(LessonAppResponse.Contact c) {
        return LessonWebResponse.Contact.builder()
                .seq(c.getSeq())
                .type(c.getType().getCode())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
