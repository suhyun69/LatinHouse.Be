package com.latinhouse.backend.adapter.in.web.lesson.mapper.strategy;

import com.latinhouse.backend.adapter.in.web.lesson.dto.UpdateLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.WebToAppStrategy;
import com.latinhouse.backend.application.domain.lesson.ContactType;
import com.latinhouse.backend.application.domain.lesson.DiscountType;
import com.latinhouse.backend.application.domain.lesson.Region;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import com.latinhouse.backend.util.DateTimeUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateWebToApp implements WebToAppStrategy<UpdateLessonWebRequest, UpdateLessonAppRequest> {

    @Override public boolean supports(Class<?> w, Class<?> a) {
        return UpdateLessonWebRequest.class.isAssignableFrom(w)
                && UpdateLessonAppRequest.class.isAssignableFrom(a);
    }

    @Override public UpdateLessonAppRequest toAppReq(UpdateLessonWebRequest webReq) {
        return UpdateLessonAppRequest.builder()
                .no(webReq.getNo())
                .title(webReq.getTitle())
                .genre(webReq.getGenre())
                .instructorLo(webReq.getInstructorLo())
                .instructorLa(webReq.getInstructorLa())
                .options(webReq.getOptions().stream().map(UpdateWebToApp::convertTo).toList())
                .bank(webReq.getBank())
                .accountNumber(webReq.getAccountNumber())
                .accountOwner(webReq.getAccountOwner())
                .discounts(Optional.ofNullable(webReq.getDiscounts()).orElse(List.of()).stream().map(UpdateWebToApp::convertTo).toList())
                .maxDiscountAmount(webReq.getMaxDiscountAmount())
                .contacts(webReq.getContacts().stream().map(UpdateWebToApp::convertTo).toList())
                .build();
    }

    private static UpdateLessonAppRequest.Option convertTo(UpdateLessonWebRequest.Option o) {

        LocalDateTime startDateTime = DateTimeUtil.toLocalDateTime(o.getStartDate(), o.getStartTime());
        LocalDateTime endDateTime = DateTimeUtil.toLocalDateTime(o.getEndDate(), o.getEndTime());

        return UpdateLessonAppRequest.Option.builder()
                .seq(o.getSeq())
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .region(Region.of(o.getRegion()))
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static UpdateLessonAppRequest.Discount convertTo(UpdateLessonWebRequest.Discount d) {
        return UpdateLessonAppRequest.Discount.builder()
                .seq(d.getSeq())
                .type(DiscountType.of(d.getType()))
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static UpdateLessonAppRequest.Contact convertTo(UpdateLessonWebRequest.Contact c) {
        return UpdateLessonAppRequest.Contact.builder()
                .seq(c.getSeq())
                .type(ContactType.of(c.getType()))
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
