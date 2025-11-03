package com.latinhouse.backend.application.my.mapper.strategy;

import com.latinhouse.backend.common.mapper.AppToCommandStrategy;
import com.latinhouse.backend.common.mapper.DomainToAppStrategy;
import com.latinhouse.backend.domain.lesson.*;
import com.latinhouse.backend.domain.lesson.command.AddLessonCommand;
import com.latinhouse.backend.port.in.my.dto.AddLessonAppRequest;
import com.latinhouse.backend.port.in.my.dto.AddLessonAppResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AddLessonAppStrategy implements
        AppToCommandStrategy<AddLessonAppRequest, AddLessonCommand>,
        DomainToAppStrategy<Lesson, AddLessonAppResponse> {

    @Override
    public boolean appToCommandSupports(Class<?> c, Class<?> d) {
        return AddLessonAppRequest.class.isAssignableFrom(c)
                && AddLessonCommand.class.isAssignableFrom(d);
    }

    @Override
    public AddLessonCommand toCommand(AddLessonAppRequest appReq) {
        return AddLessonCommand.builder()
                .title(appReq.getTitle())
                .genre(appReq.getGenre())
                .instructorLa(appReq.getInstructorLa())
                .instructorLo(appReq.getInstructorLo())
                .options(appReq.getOptions().stream()
                        .map(this::toDomainOption)
                        .collect(Collectors.toList()))
                .bank(appReq.getBank())
                .accountNumber(appReq.getAccountNumber())
                .accountOwner(appReq.getAccountOwner())
                .discounts(appReq.getDiscounts() != null ?
                        appReq.getDiscounts().stream()
                                .map(this::toDomainDiscount)
                                .collect(Collectors.toList()) : null)
                .maxDiscountAmount(appReq.getMaxDiscountAmount())
                .contacts(appReq.getContacts() != null ?
                        appReq.getContacts().stream()
                                .map(this::toDomainContact)
                                .collect(Collectors.toList()) : null)
                .build();
    }

    private Option toDomainOption(AddLessonAppRequest.Option appOption) {
        return Option.builder()
                .seq(null)
                .startDateTime(appOption.getStartDateTime())
                .endDateTime(appOption.getEndDateTime())
                .region(appOption.getRegion())
                .location(appOption.getLocation())
                .locationUrl(appOption.getLocationUrl())
                .price(appOption.getPrice())
                .build();
    }

    private Discount toDomainDiscount(AddLessonAppRequest.Discount appDiscount) {
        return Discount.builder()
                .seq(null)
                .type(appDiscount.getType())
                .condition(appDiscount.getCondition())
                .amount(appDiscount.getAmount())
                .build();
    }

    private Contact toDomainContact(AddLessonAppRequest.Contact appContact) {
        return Contact.builder()
                .seq(null)
                .type(appContact.getType())
                .name(appContact.getName())
                .address(appContact.getAddress())
                .build();
    }

    @Override
    public boolean domainToAppSupports(Class<?> c, Class<?> d) {
        return Lesson.class.isAssignableFrom(c)
                && AddLessonAppResponse.class.isAssignableFrom(d);
    }

    @Override
    public AddLessonAppResponse toAppRes(Lesson lesson) {
        return AddLessonAppResponse.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .build();
    }
}
