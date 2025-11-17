package com.latinhouse.backend.adapter.in.web.checkout.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.domain.lesson.Discount;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component("Checkout.GetCheckoutWebStrategy")
public class GetCheckoutWebStrategy implements
        WebToAppStrategy<GetCheckoutWebRequest, GetCheckoutAppRequest>,
        AppToWebStrategy<GetCheckoutAppResponse, GetCheckoutWebResponse> {

    @Override
    public boolean webToAppSupports(Class<?> c, Class<?> d) {
        return GetCheckoutWebRequest.class.isAssignableFrom(c)
                && GetCheckoutAppRequest.class.isAssignableFrom(d);
    }

    @Override
    public GetCheckoutAppRequest toAppReq(GetCheckoutWebRequest webReq) {
        return GetCheckoutAppRequest.builder()
                .checkoutId(webReq.getCheckoutId())
                .build();
    }

    @Override public boolean appToWebSupports(Class<?> a, Class<?> w) {
        return GetCheckoutAppResponse.class.isAssignableFrom(a)
                && GetCheckoutWebResponse.class.isAssignableFrom(w);
    }

    @Override
    public GetCheckoutWebResponse toWebRes(GetCheckoutAppResponse appRes) {
        return GetCheckoutWebResponse.builder()
                .checkoutId(appRes.getCheckoutId())
                .status(appRes.getStatus().getCode())
                .lessonTitle(appRes.getLessonTitle())
                .lessonNo(appRes.getLessonNo())
                .genre(appRes.getGenre().getCode())
                .instructorLoNickname(appRes.getInstructorLoNickname())
                .instructorLoId(appRes.getInstructorLoId())
                .instructorLaNickname(appRes.getInstructorLaNickname())
                .instructorLaId(appRes.getInstructorLaId())
                .startDate(appRes.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(appRes.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .startTime(appRes.getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endTime(appRes.getEndDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .region(appRes.getRegion().getCode())
                .location(appRes.getLocation())
                .price(appRes.getPrice())
                .discounts(appRes.getDiscounts().stream()
                    .map(this::toWebDiscount)
                    .collect(Collectors.toList()))
                .build();
    }

    private GetCheckoutWebResponse.Discount toWebDiscount(GetCheckoutAppResponse.Discount discount) {
        return GetCheckoutWebResponse.Discount.builder()
                .no(discount.getNo())
                .type(discount.getType())
                .condition(discount.getCondition())
                .amount(discount.getAmount())
                .build();
    }
}
