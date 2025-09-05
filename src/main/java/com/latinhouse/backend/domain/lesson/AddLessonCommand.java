package com.latinhouse.backend.domain.lesson;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class AddLessonCommand {
    String title;
    Genre genre;
    String instructorLo;
    String instructorLa;
    List<Option>  options;
    String bank;
    String accountNumber;
    String accountOwner;
    List<Discount>  discounts;
    BigDecimal maxDiscountAmount;
    List<Contact>  contacts;

    @Value
    @Builder
    public static class Option {
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        Region region;
        String location;
        String locationUrl;
        BigDecimal price;
    }

    @Value
    @Builder
    public static class Discount {
        DiscountType type;
        String condition;
        BigDecimal amount;
    }

    @Value
    @Builder
    public static class Contact {
        ContactType type;
        String name;
        String address;
    }
}
