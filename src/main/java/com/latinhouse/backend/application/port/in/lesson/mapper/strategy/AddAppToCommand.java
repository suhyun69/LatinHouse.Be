package com.latinhouse.backend.application.port.in.lesson.mapper.strategy;

import com.latinhouse.backend.application.domain.lesson.service.AddLessonCommand;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.mapper.AppToCommandStrategy;
import org.springframework.stereotype.Component;

@Component
public class AddAppToCommand implements AppToCommandStrategy<AddLessonAppRequest, AddLessonCommand> {

    @Override
    public boolean supports(Class<?> a, Class<?> c) {
        return AddLessonAppRequest.class.isAssignableFrom(a)
                && AddLessonCommand.class.isAssignableFrom(c);
    }

    @Override
    public AddLessonCommand toCommand(AddLessonAppRequest appReq) {
        return AddLessonCommand.builder()
                .title(appReq.getTitle())
                .genre(appReq.getGenre())
                .instructorLo(appReq.getInstructorLo())
                .instructorLa(appReq.getInstructorLa())
                .options(appReq.getOptions().stream().map(AddAppToCommand::convertTo).toList())
                .bank(appReq.getBank())
                .accountNumber(appReq.getAccountNumber())
                .accountOwner(appReq.getAccountOwner())
                .discounts(appReq.getDiscounts().stream().map(AddAppToCommand::convertTo).toList())
                .maxDiscountAmount(appReq.getMaxDiscountAmount())
                .contacts(appReq.getContacts().stream().map(AddAppToCommand::convertTo).toList())
                .isActive(true)
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
                .isActive(true)
                .build();
    }

    private static AddLessonCommand.Discount convertTo(AddLessonAppRequest.Discount d) {
        return AddLessonCommand.Discount.builder()
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .isActive(true)
                .build();
    }

    private static AddLessonCommand.Contact convertTo(AddLessonAppRequest.Contact c) {
        return AddLessonCommand.Contact.builder()
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .isActive(true)
                .build();
    }
}
