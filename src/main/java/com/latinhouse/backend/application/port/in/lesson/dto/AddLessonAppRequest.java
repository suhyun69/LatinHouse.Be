package com.latinhouse.backend.application.port.in.lesson.dto;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebRequest;
import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.common.exception.BadRequestException;
import com.latinhouse.backend.domain.lesson.*;
import com.latinhouse.backend.util.DateTimeUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Value
@EqualsAndHashCode(callSuper = false)
public class AddLessonAppRequest extends SelfValidating<AddLessonAppRequest> {

    @NotBlank(message = "title cannot be blank.")
    String title;

    @NotNull(message = "genre cannot be null.")
    Genre genre;

    String instructorLa;
    String instructorLo;

    @Valid
    @NotNull(message = "options cannot be null.")
    @Size(min = 1, message = "options must contain at least one item")
    List<Option> options;

    @NotBlank(message = "bank cannot be blank.")
    String bank;

    @NotBlank(message = "accountNumber cannot be blank.")
    String accountNumber;

    @NotBlank(message = "accountOwner cannot be blank.")
    String accountOwner;

    @Valid
    List<Discount> discounts;
    BigDecimal maxDiscountAmount;

    @Valid
    @NotNull(message = "contacts cannot be null.")
    @Size(min = 1, message = "contacts must contain at least one item")
    List<Contact> contacts;

    @Value
    @Builder
    public static class Option {
        @NotNull(message = "option.startDateType cannot be null.")
        LocalDateTime startDateTime;

        @NotNull(message = "option.endDAteType cannot be null.")
        LocalDateTime endDateTime;
        Region region;
        String location;
        String locationUrl;

        @Min(value = 0, message = "option.price must be greater than or equal to 0")
        BigDecimal price;
    }

    @Value
    @Builder
    public static class Discount {
        @NotNull(message = "discount.type cannot be null.")
        DiscountType type;
        String condition;

        @Min(value = 0, message = "discount.amount must be greater than or equal to 0")
        BigDecimal amount;
    }

    @Value
    @Builder
    public static class Contact {
        @NotNull(message = "contact.type cannot be null.")
        ContactType type;
        String name;

        @NotBlank(message = "contact.address cannot be blank")
        String address;
    }

    @Builder
    public AddLessonAppRequest(String title
            , String genre
            , String instructorLa
            , String instructorLo
            , List<AddLessonWebRequest.Option> options
            , String bank
            , String accountNumber
            , String accountOwner
            , List<AddLessonWebRequest.Discount> discounts
            , BigDecimal maxDiscountAmount
            , List<AddLessonWebRequest.Contact> contacts
    ) {
        this.title = title;
        this.genre = Genre.of(genre);
        this.instructorLo = instructorLo;
        this.instructorLa = instructorLa;
        this.options = Optional.ofNullable(options).orElse(List.of()).stream()
                .filter(Objects::nonNull)
                .map(AddLessonAppRequest::convertTo)
                .toList();
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.discounts = Optional.ofNullable(discounts).orElse(List.of()).stream()
                .filter(Objects::nonNull)
                .map(AddLessonAppRequest::convertTo)
                .toList();
        this.maxDiscountAmount = maxDiscountAmount;
        this.contacts = Optional.ofNullable(contacts).orElse(List.of()).stream()
                .filter(Objects::nonNull)
                .map(AddLessonAppRequest::convertTo)
                .toList();

        if(instructorLo == null && instructorLa == null) {
            throw new BadRequestException(
                    String.format("instructorLo and instructorLa both null are not allowed")
            );
        }

        this.validateSelf();
    }

    private static Option convertTo(AddLessonWebRequest.Option o) {

        LocalDateTime startDateTime = DateTimeUtil.toLocalDateTime(o.getStartDate(), o.getStartTime());
        LocalDateTime endDateTime = DateTimeUtil.toLocalDateTime(o.getEndDate(), o.getEndTime());

        if (!startDateTime.isBefore(endDateTime)) {
            throw new BadRequestException(
                    String.format("startDateTime(%s) must be before endDateTime(%s)", startDateTime, endDateTime)
            );
        }

        return Option.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .region(Region.of(o.getRegion()))
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static Discount convertTo(AddLessonWebRequest.Discount d) {
        return Discount.builder()
                .type(DiscountType.of(d.getType()))
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static Contact convertTo(AddLessonWebRequest.Contact c) {
        return Contact.builder()
                .type(ContactType.of(c.getType()))
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
