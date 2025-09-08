package com.latinhouse.backend.application.port.in.lesson.dto;

import com.latinhouse.backend.application.domain.lesson.ContactType;
import com.latinhouse.backend.application.domain.lesson.DiscountType;
import com.latinhouse.backend.application.domain.lesson.Genre;
import com.latinhouse.backend.application.domain.lesson.Region;
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
        private Long seq;
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
        private Long seq;
        private DiscountType type;
        private String condition;
        private BigDecimal amount;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Contact {
        private Long seq;
        private ContactType type;
        private String name;
        private String address;
    }
}
