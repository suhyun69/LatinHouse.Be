package com.latinhouse.backend.adapter.in.web.lesson.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class GetLessonWebResponse {
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
    public static class Option {
        Long no;
        String startDate;
        String endDate;
        String startTime;
        String endTime;
        String region;
        String location;
        String locationUrl;
        BigDecimal price;
    }

    @Getter
    @Builder
    public static class Discount {
        Long no;
        String type;
        String condition;
        BigDecimal amount;
    }

    @Getter
    @Builder
    public static class Contact {
        Long no;
        String type;
        String name;
        String address;
    }
}
