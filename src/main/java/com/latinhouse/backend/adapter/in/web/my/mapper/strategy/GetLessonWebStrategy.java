package com.latinhouse.backend.adapter.in.web.my.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.my.dto.GetLessonWebResponse;
import com.latinhouse.backend.common.mapper.AppToWebStrategy;
import com.latinhouse.backend.domain.lesson.Contact;
import com.latinhouse.backend.domain.lesson.Discount;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.port.in.my.dto.GetLessonAppResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component("My.GetLessonWebStrategy")
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
                .genre(appRes.getGenre().getCode())
                .instructorLa(appRes.getInstructorLa())
                .instructorLo(appRes.getInstructorLo())
                .options(appRes.getOptions().stream()
                        .map(this::toAppOption)
                        .collect(Collectors.toList()))
                .bank(appRes.getBank())
                .accountNumber(appRes.getAccountNumber())
                .accountOwner(appRes.getAccountOwner())
                .discounts(appRes.getDiscounts().stream()
                        .map(this::toAppDiscount)
                        .collect(Collectors.toList()))
                .maxDiscountAmount(appRes.getMaxDiscountAmount())
                .contacts(appRes.getContacts().stream()
                        .map(this::toAppContact)
                        .collect(Collectors.toList()))
                .build();
    }

    private GetLessonWebResponse.Option toAppOption(GetLessonAppResponse.Option option) {
        return GetLessonWebResponse.Option.builder()
                .seq(option.getSeq())
                .startDate(option.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(option.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .startTime(option.getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .endTime(option.getStartDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                .region(option.getRegion().getCode())
                .location(option.getLocation())
                .locationUrl(option.getLocationUrl())
                .price(option.getPrice())
                .build();
    }

    private GetLessonWebResponse.Discount toAppDiscount(GetLessonAppResponse.Discount discount) {
        return GetLessonWebResponse.Discount.builder()
                .seq(discount.getSeq())
                .type(discount.getType().getCode())
                .condition(discount.getCondition())
                .amount(discount.getAmount())
                .build();
    }

    private GetLessonWebResponse.Contact toAppContact(GetLessonAppResponse.Contact contact) {
        return GetLessonWebResponse.Contact.builder()
                .seq(contact.getSeq())
                .type(contact.getType().getCode())
                .name(contact.getName())
                .address(contact.getAddress())
                .build();
    }
}
