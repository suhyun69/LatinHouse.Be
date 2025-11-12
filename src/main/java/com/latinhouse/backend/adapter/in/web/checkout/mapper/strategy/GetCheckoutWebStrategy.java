package com.latinhouse.backend.adapter.in.web.checkout.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebRequest;
import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppRequest;
import com.latinhouse.backend.port.in.checkout.dto.GetCheckoutAppResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

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
                .status(appRes.getStatus())
                .lessonTitle(appRes.getLessonTitle())
                .lessonNo(appRes.getLessonNo())
                .genre(appRes.getGenre().getCode())
                .instructorLoNickname(appRes.getInstructorLo().getNickname())
                .instructorLoId(appRes.getInstructorLo().getId())
                .instructorLaNickname(appRes.getInstructorLa().getNickname())
                .instructorLaId(appRes.getInstructorLa().getId())
                .startDate(appRes.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(appRes.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .startTime(appRes.getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endTime(appRes.getEndDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .amount(appRes.getAmount())
                .region(appRes.getRegion().getCode())
                .location(appRes.getLocation())
                .build();
    }
}
