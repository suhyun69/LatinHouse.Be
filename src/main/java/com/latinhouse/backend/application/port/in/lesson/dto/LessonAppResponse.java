package com.latinhouse.backend.application.port.in.lesson.dto;

import com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse;
import com.latinhouse.backend.domain.lesson.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LessonAppResponse {

    private Long no;
    private String title;
    private Genre genre;
    private String instructorLa;
    private String instructorLo;
    private List<Option> options;
    private String bank;
    private String accountNumber;
    private String accountOwner;
    private List<Discount> discounts;
    private BigDecimal maxDiscountAmount;
    private List<Contact> contacts;

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Option {
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private Region region;
        private String location;
        private String locationUrl;
        private BigDecimal price;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Discount {
        private DiscountType type;
        private String condition;
        private BigDecimal amount;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Contact {
        private ContactType type;
        private String name;
        private String address;
    }

    public static LessonAppResponse from(Lesson lesson) {
        return LessonAppResponse.builder()
                .no(lesson.getNo())
                .title(lesson.getTitle())
                .genre(lesson.getGenre())
                .instructorLo(lesson.getInstructorLo())
                .instructorLa(lesson.getInstructorLa())
                .options(lesson.getOptions().stream().map(LessonAppResponse::convertTo).toList())
                .bank(lesson.getBank())
                .accountNumber(lesson.getAccountNumber())
                .accountOwner(lesson.getAccountOwner())
                .discounts(lesson.getDiscounts().stream().map(LessonAppResponse::convertTo).toList())
                .maxDiscountAmount(lesson.getMaxDiscountAmount())
                .contacts(lesson.getContacts().stream().map(LessonAppResponse::convertTo).toList())
                .build();
    }

    private static Option convertTo(com.latinhouse.backend.domain.lesson.Option o) {
        return Option.builder()
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static Discount convertTo(com.latinhouse.backend.domain.lesson.Discount d) {
        return Discount.builder()
                .type(d.getType())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static Contact convertTo(com.latinhouse.backend.domain.lesson.Contact c) {
        return Contact.builder()
                .type(c.getType())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }

    public LessonWebResponse toWebRes() {
        return LessonWebResponse.builder()
                .no(this.no)
                .title(this.title)
                .genre(this.genre.getCode())
                .instructorLo(this.instructorLo)
                .instructorLa(this.instructorLa)
                .options(this.options.stream().map(LessonAppResponse::convertTo).toList())
                .bank(this.bank)
                .accountNumber(this.accountNumber)
                .accountOwner(this.accountOwner)
                .discounts(this.discounts.stream().map(LessonAppResponse::convertTo).toList())
                .maxDiscountAmount(this.maxDiscountAmount)
                .contacts(this.contacts.stream().map(LessonAppResponse::convertTo).toList())
                .build();
    }

    private static com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Option convertTo(Option o) {
        return com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Option.builder()
                .startDateTime(o.getStartDateTime())
                .endDateTime(o.getEndDateTime())
                .region(o.getRegion().getCode())
                .location(o.getLocation())
                .locationUrl(o.getLocationUrl())
                .price(o.getPrice())
                .build();
    }

    private static com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Discount convertTo(Discount d) {
        return com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Discount.builder()
                .type(d.getType().getCode())
                .condition(d.getCondition())
                .amount(d.getAmount())
                .build();
    }

    private static com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Contact convertTo(Contact c) {
        return com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse.Contact.builder()
                .type(c.getType().getCode())
                .name(c.getName())
                .address(c.getAddress())
                .build();
    }
}
