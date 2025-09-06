package com.latinhouse.backend.adapter.in.web.lesson.dto;

import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.LessonMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddLessonWebRequest {

    @NotBlank(message = "title cannot be blank.")
    String title;

    @NotNull(message = "genre cannot be null.")
    @Pattern(regexp = "^[SB]$", message = "genre must be 'S' or 'B'")
    String genre;

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

        @NotBlank(message = "option.startDate must not be null")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "option.startDateTime must be in 'yyyy-MM-dd' format")
        String startDate;

        @NotBlank(message = "option.endDate must not be null")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "option.endDateTime must be in 'yyyy-MM-dd' format")
        String endDate;

        @NotBlank(message = "option.startTime must not be null")
        @Pattern(regexp = "\\d{2}:\\d{2}", message = "option.startDateTime must be in 'HH:mm' format")
        String startTime;

        @NotBlank(message = "option.endTime must not be null")
        @Pattern(regexp = "\\d{2}:\\d{2}", message = "option.endDateTime must be in 'HH:mm' format")
        String endTime;

        @NotBlank(message = "option.region must not be null")
        @Pattern(regexp = "^(GN|HD)$", message = "option.region must be 'GN' or 'HD'")
        String region;
        String location;
        String locationUrl;

        @NotNull(message = "option.price must not be null")
        @Min(value = 0, message = "option.price must be greater than or equal to 0")
        BigDecimal price;
    }

    @Value
    @Builder
    public static class Discount {
        @Pattern(regexp = "^[ES]$", message = "discount.type must be 'E' or 'S'")
        String type;
        String condition;

        @NotNull(message = "discount.amount must not be null")
        @Min(value = 0, message = "discount.amount must be greater than or equal to 0")
        BigDecimal amount;
    }

    @Value
    @Builder
    public static class Contact {
        @Pattern(regexp = "^[PKWI]$", message = "contact.type must be 'P' or 'K' or 'W' or 'I'")
        String type;
        String name;

        @NotBlank(message = "contact.address cannot be blank")
        String address;
    }
}
