package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.domain.lesson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddLessonImpl implements AddLessonUseCase {

    private final LessonService lessonService;

    @Override
    @Transactional
    public AddLessonAppResponse addLesson(AddLessonAppRequest appReq) {

        List<AddLessonCommand.Option> options = Optional.ofNullable(appReq.getOptions()).orElse(List.of()).stream()
                .map(AddLessonImpl::convertTo)
                .toList();
        List<AddLessonCommand.Discount> discounts = Optional.ofNullable(appReq.getDiscounts()).orElse(List.of()).stream()
                .map(AddLessonImpl::convertTo)
                .toList();
        List<AddLessonCommand.Contact> contacts = Optional.ofNullable(appReq.getContacts()).orElse(List.of()).stream()
                .map(AddLessonImpl::convertTo)
                .toList();

        AddLessonCommand cmd = AddLessonCommand.builder()
                .title(appReq.getTitle())
                .genre(appReq.getGenre())
                .instructorLo(appReq.getInstructorLo())
                .instructorLa(appReq.getInstructorLa())
                .options(options)
                .bank(appReq.getBank())
                .accountNumber(appReq.getAccountNumber())
                .accountOwner(appReq.getAccountOwner())
                .discounts(discounts)
                .maxDiscountAmount(appReq.getMaxDiscountAmount())
                .contacts(contacts)
                .build();
        Lesson lesson = lessonService.addLesson(cmd);

        return AddLessonAppResponse.from(lesson);
    }

    private static AddLessonCommand.Option convertTo(AddLessonAppRequest.Option o) {
        return AddLessonCommand.Option.builder()
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static AddLessonCommand.Discount convertTo(AddLessonAppRequest.Discount d) {
        return AddLessonCommand.Discount.builder()
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static AddLessonCommand.Contact convertTo(AddLessonAppRequest.Contact c) {
        return AddLessonCommand.Contact.builder()
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
