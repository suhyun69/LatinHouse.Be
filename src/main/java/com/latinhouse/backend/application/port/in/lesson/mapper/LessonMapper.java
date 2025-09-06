package com.latinhouse.backend.application.port.in.lesson.mapper;

import com.latinhouse.backend.application.domain.lesson.Contact;
import com.latinhouse.backend.application.domain.lesson.Discount;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.Option;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.application.domain.lesson.service.AddLessonCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("lessonPortMapper")
@RequiredArgsConstructor
public class LessonMapper {
    /*
        AppRequest -> Command
        Domain -> AppResponse
     */
    public AddLessonCommand toCommand(AddLessonAppRequest appReq) {
        return AddLessonCommand.builder()
                .title(appReq.getTitle())
                .genre(appReq.getGenre())
                .instructorLo(appReq.getInstructorLo())
                .instructorLa(appReq.getInstructorLa())
                .options(appReq.getOptions().stream().map(LessonMapper::convertTo).toList())
                .bank(appReq.getBank())
                .accountNumber(appReq.getAccountNumber())
                .accountOwner(appReq.getAccountOwner())
                .discounts(appReq.getDiscounts().stream().map(LessonMapper::convertTo).toList())
                .maxDiscountAmount(appReq.getMaxDiscountAmount())
                .contacts(appReq.getContacts().stream().map(LessonMapper::convertTo).toList())
                .build();
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

    public AddLessonAppResponse toAddLessonAppRes(Lesson lesson) {
        return AddLessonAppResponse.builder()
                .no(lesson.getNo())
                .build();
    }

    public LessonAppResponse toAppRes(Lesson lesson) {
        return LessonAppResponse.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(lesson.getOptions().stream().map(LessonMapper::convertTo).toList())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts().stream().map(LessonMapper::convertTo).toList())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts().stream().map(LessonMapper::convertTo).toList())
                .build();
    }

    private static LessonAppResponse.Option convertTo(Option o) {
        return LessonAppResponse.Option.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static LessonAppResponse.Discount convertTo(Discount d) {
        return LessonAppResponse.Discount.builder()
                .seq(d.getSeq())
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static LessonAppResponse.Contact convertTo(Contact c) {
        return LessonAppResponse.Contact.builder()
                .seq(c.getSeq())
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }

}
