package com.latinhouse.backend.domain.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    private Long no;
    private String title;
    private Genre genre;
    private String instructorLo;
    private String instructorLa;
    private List<Option> options;
    private String bank;
    private String accountNumber;
    private String accountOwner;
    private List<Discount> discounts;
    private Integer maxDiscountAmount;
    private List<Contact> contacts;
}
