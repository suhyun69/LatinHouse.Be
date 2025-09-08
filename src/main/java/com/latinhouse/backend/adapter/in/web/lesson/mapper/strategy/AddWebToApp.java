package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.WebToAppStrategy;
import com.latinhouse.backend.application.domain.lesson.ContactType;
import com.latinhouse.backend.application.domain.lesson.DiscountType;
import com.latinhouse.backend.application.domain.lesson.Region;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AddWebToApp implements WebToAppStrategy<AddLessonWebRequest, AddLessonAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
        return AddLessonWebRequest.class.isAssignableFrom(w)
                && AddLessonAppRequest.class.isAssignableFrom(a);
    }

    @Override public AddLessonAppRequest toAppReq(AddLessonWebRequest webReq) {
        return AddLessonAppRequest.builder()
                .title(webReq.getTitle())
                .genre(webReq.getGenre())
                .instructorLo(webReq.getInstructorLo())
                .instructorLa(webReq.getInstructorLa())
                .options(webReq.getOptions().stream().map(AddWebToApp::convertTo).toList())
                .bank(webReq.getBank())
                .accountNumber(webReq.getAccountNumber())
                .accountOwner(webReq.getAccountOwner())
                .discounts(Optional.ofNullable(webReq.getDiscounts()).orElse(List.of()).stream().map(AddWebToApp::convertTo).toList())
                .maxDiscountAmount(webReq.getMaxDiscountAmount())
                .contacts(webReq.getContacts().stream().map(AddWebToApp::convertTo).toList())
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
}