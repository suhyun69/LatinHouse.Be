package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.AddLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.my.dto.AddLessonWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.common.mapper.WebToAppStrategy;
import com.latinhouse.backend.domain.lesson.ContactType;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Region;
import com.latinhouse.backend.port.in.my.dto.AddLessonAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddLessonAppResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
public class AddLessonWebStrategy implements
        WebToAppStrategy<AddLessonWebRequest, AddLessonAppRequest>,
        AppToWebStrategy<AddLessonAppResponse, AddLessonWebResponse> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public boolean webToAppSupports(Class<?> c, Class<?> d) {
        return AddLessonWebRequest.class.isAssignableFrom(c)
                && AddLessonAppRequest.class.isAssignableFrom(d);
    }

    @Override
    public AddLessonAppRequest toAppReq(AddLessonWebRequest webReq) {
        return AddLessonAppRequest.builder()
                .title(webReq.getTitle())
                .genre(webReq.getGenre())
                .instructorLa(webReq.getInstructorLa())
                .instructorLo(webReq.getInstructorLo())
                .options(webReq.getOptions() != null ?
                        webReq.getOptions().stream()
                                .map(this::toAppOption)
                                .collect(Collectors.toList()) : null)
                .bank(webReq.getBank())
                .accountNumber(webReq.getAccountNumber())
                .accountOwner(webReq.getAccountOwner())
                .discounts(webReq.getDiscounts() != null ?
                        webReq.getDiscounts().stream()
                                .map(this::toAppDiscount)
                                .collect(Collectors.toList()) : null)
                .maxDiscountAmount(webReq.getMaxDiscountAmount())
                .contacts(webReq.getContacts() != null ?
                        webReq.getContacts().stream()
                                .map(this::toAppContact)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    private AddLessonAppRequest.Option toAppOption(AddLessonWebRequest.Option webOption) {
        LocalDate startDate = LocalDate.parse(webOption.getStartDate(), DATE_FORMATTER);
        LocalTime startTime = LocalTime.parse(webOption.getStartTime(), TIME_FORMATTER);
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        LocalDate endDate = LocalDate.parse(webOption.getEndDate(), DATE_FORMATTER);
        LocalTime endTime = LocalTime.parse(webOption.getEndTime(), TIME_FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        return AddLessonAppRequest.Option.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .region(webOption.getRegion() != null ? Region.of(webOption.getRegion()) : null)
                .location(webOption.getLocation())
                .locationUrl(webOption.getLocationUrl())
                .price(webOption.getPrice())
                .build();
    }

    private AddLessonAppRequest.Discount toAppDiscount(AddLessonWebRequest.Discount webDiscount) {
        return AddLessonAppRequest.Discount.builder()
                .type(webDiscount.getType() != null ? DiscountType.of(webDiscount.getType()) : null)
                .condition(webDiscount.getCondition())
                .amount(webDiscount.getAmount())
                .build();
    }

    private AddLessonAppRequest.Contact toAppContact(AddLessonWebRequest.Contact webContact) {
        return AddLessonAppRequest.Contact.builder()
                .type(webContact.getType() != null ? ContactType.of(webContact.getType()) : null)
                .name(webContact.getName())
                .address(webContact.getAddress())
                .build();
    }

    @Override
    public boolean appToWebSupports(Class<?> c, Class<?> d) {
        return AddLessonAppResponse.class.isAssignableFrom(c)
                && AddLessonWebResponse.class.isAssignableFrom(d);
    }

    @Override
    public AddLessonWebResponse toWebRes(AddLessonAppResponse appRes) {
        return AddLessonWebResponse.builder()
                .no(appRes.getNo())
                .title(appRes.getTitle())
                .build();
    }
}
