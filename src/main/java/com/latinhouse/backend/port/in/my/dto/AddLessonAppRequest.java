package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.common.SelfValidating;
import com.latinhouse.backend.common.exception.BadRequestException;
import com.latinhouse.backend.domain.lesson.ContactType;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Genre;
import com.latinhouse.backend.domain.lesson.Region;
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
            , List<Option> options
            , String bank
            , String accountNumber
            , String accountOwner
            , List<Discount> discounts
            , BigDecimal maxDiscountAmount
            , List<Contact> contacts
    ) {
        this.title = title;
        this.genre = Genre.of(genre);
        this.instructorLo = instructorLo;
        this.instructorLa = instructorLa;
        this.options = options;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.discounts = discounts;
        this.maxDiscountAmount = maxDiscountAmount;
        this.contacts = contacts;

        if(instructorLo == null && instructorLa == null) {
            throw new BadRequestException(String.format("instructorLo and instructorLa both null are not allowed"), 0);
        }

        for(Option o : options) {
            if(!o.startDateTime.isBefore(o.endDateTime))
                throw new BadRequestException(String.format("startDateTime(%s) must be before endDateTime(%s)", o.startDateTime, o.endDateTime), 0);
        }

        this.validateSelf();
    }
}