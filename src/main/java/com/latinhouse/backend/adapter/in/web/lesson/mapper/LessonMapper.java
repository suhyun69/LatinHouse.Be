package com.latinhouse.backend.adapter.in.web.lesson.mapper;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.domain.lesson.ContactType;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Region;
import com.latinhouse.backend.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component("lessonWebMapper")
@RequiredArgsConstructor
public class LessonMapper {
    /*
        WebRequest -> AppRequest
        AppResponse -> WebResponse
     */
    public AddLessonAppRequest toAppReq(AddLessonWebRequest webReq) {
        return AddLessonAppRequest.builder()
                .title(webReq.getTitle())
                .genre(webReq.getGenre())
                .instructorLo(webReq.getInstructorLo())
                .instructorLa(webReq.getInstructorLa())
                .options(webReq.getOptions().stream().map(LessonMapper::convertTo).toList())
                .bank(webReq.getBank())
                .accountNumber(webReq.getAccountNumber())
                .accountOwner(webReq.getAccountOwner())
                .discounts(Optional.ofNullable(webReq.getDiscounts()).orElse(List.of()).stream().map(LessonMapper::convertTo).toList())
                .maxDiscountAmount(webReq.getMaxDiscountAmount())
                .contacts(webReq.getContacts().stream().map(LessonMapper::convertTo).toList())
                .build();
    }

    private static AddLessonAppRequest.Option convertTo(AddLessonWebRequest.Option o) {

        LocalDateTime startDateTime = DateTimeUtil.toLocalDateTime(o.getStartDate(), o.getStartTime());
        LocalDateTime endDateTime = DateTimeUtil.toLocalDateTime(o.getEndDate(), o.getEndTime());

        return AddLessonAppRequest.Option.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .region(Region.of(o.getRegion()))
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static AddLessonAppRequest.Discount convertTo(AddLessonWebRequest.Discount d) {
        return AddLessonAppRequest.Discount.builder()
                .type(DiscountType.of(d.getType()))
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static AddLessonAppRequest.Contact convertTo(AddLessonWebRequest.Contact c) {
        return AddLessonAppRequest.Contact.builder()
                .type(ContactType.of(c.getType()))
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }

    public AddLessonWebResponse toWebRes(AddLessonAppResponse appRes) {
        return AddLessonWebResponse.builder()
                .no(appRes.getNo())
                .build();
    }

    public LessonWebResponse toWebRes(LessonAppResponse appRes) {
        return LessonWebResponse.builder()
                .no(appRes.getNo())
                .title(appRes.getTitle())
                .genre(appRes.getGenre().getCode())
                .instructorLo(appRes.getInstructorLo())
                .instructorLa(appRes.getInstructorLa())
                .options(appRes.getOptions().stream().map(LessonMapper::convertTo).toList())
                .bank(appRes.getBank())
                .accountNumber(appRes.getAccountNumber())
                .accountOwner(appRes.getAccountOwner())
                .discounts(appRes.getDiscounts().stream().map(LessonMapper::convertTo).toList())
                .maxDiscountAmount(appRes.getMaxDiscountAmount())
                .contacts(appRes.getContacts().stream().map(LessonMapper::convertTo).toList())
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
