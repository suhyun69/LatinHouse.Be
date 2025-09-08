package com.latinhouse.backend.application.port.in.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.service.UpdateLessonCommand;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.mapper.AppToCommandStrategy;
import org.springframework.stereotype.Component;

@Component
public class UpdateAppToCommand implements AppToCommandStrategy<UpdateLessonAppRequest, UpdateLessonCommand> {

    @Override
    public boolean supports(Class<?> a, Class<?> c) {
        return UpdateLessonAppRequest.class.isAssignableFrom(a)
                && UpdateLessonCommand.class.isAssignableFrom(c);
    }

    @Override
    public UpdateLessonCommand toCommand(UpdateLessonAppRequest appReq) {
        return UpdateLessonCommand.builder()
                .no(appReq.getNo())
                .title(appReq.getTitle())
                .genre(appReq.getGenre())
                .instructorLo(appReq.getInstructorLo())
                .instructorLa(appReq.getInstructorLa())
                .options(appReq.getOptions().stream().map(UpdateAppToCommand::convertTo).toList())
                .bank(appReq.getBank())
                .accountNumber(appReq.getAccountNumber())
                .accountOwner(appReq.getAccountOwner())
                .discounts(appReq.getDiscounts().stream().map(UpdateAppToCommand::convertTo).toList())
                .maxDiscountAmount(appReq.getMaxDiscountAmount())
                .contacts(appReq.getContacts().stream().map(UpdateAppToCommand::convertTo).toList())
                .build();
    }

    private static UpdateLessonCommand.Option convertTo(UpdateLessonAppRequest.Option o) {
        return UpdateLessonCommand.Option.builder()
                .seq(o.getSeq())
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static UpdateLessonCommand.Discount convertTo(UpdateLessonAppRequest.Discount d) {
        return UpdateLessonCommand.Discount.builder()
                .seq(d.getSeq())
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static UpdateLessonCommand.Contact convertTo(UpdateLessonAppRequest.Contact c) {
        return UpdateLessonCommand.Contact.builder()
                .seq(c.getSeq())
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
