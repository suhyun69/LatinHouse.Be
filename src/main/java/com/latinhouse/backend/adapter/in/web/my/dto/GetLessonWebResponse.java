package com.latinhouse.backend.adapter.in.web.my.dto;

import com.latinhouse.backend.domain.lesson.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class GetLessonWebResponse {
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
}
