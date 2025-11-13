package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.domain.lesson.ContactType;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Genre;
import com.latinhouse.backend.domain.lesson.Region;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetLessonAppResponse {
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

    @Data
    @Builder
    public static class Option {
        Long no;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        Region region;
        String location;
        String locationUrl;
        BigDecimal price;
    }

    @Data
    @Builder
    public static class Discount {
        Long no;
        DiscountType type;
        String condition;
        BigDecimal amount;
    }

    @Data
    @Builder
    public static class Contact {
        Long no;
        ContactType type;
        String name;
        String address;
    }
}
