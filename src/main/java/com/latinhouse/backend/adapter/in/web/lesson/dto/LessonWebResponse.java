package com.latinhouse.backend.adapter.in.web.lesson.dto;

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
public class LessonWebResponse {
    private Long no;
    private String title;
    private String genre;
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
        private String startDate;
        private String endDate;
        private String startTime;
        private String endTime;
        private String region;
        private String location;
        private String locationUrl;
        private BigDecimal price;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Discount {
        private Long seq;
        private String type;
        private String condition;
        private BigDecimal amount;
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Contact {
        private Long seq;
        private String type;
        private String name;
        private String address;
    }
}
