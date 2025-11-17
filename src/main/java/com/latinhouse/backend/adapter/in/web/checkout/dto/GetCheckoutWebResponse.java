package com.latinhouse.backend.adapter.in.web.checkout.dto;

import com.latinhouse.backend.domain.lesson.DiscountType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class GetCheckoutWebResponse {
    private String checkoutId;
    private String status;

    private String lessonTitle;
    private Long lessonNo;
    private String genre;
    private String instructorLoNickname;
    private String instructorLoId;
    private String instructorLaNickname;
    private String instructorLaId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String region;
    private String location;
    private BigDecimal price;
    private List<Discount>  discounts;

    @Getter
    @Builder
    public static class Discount {
        Long no;
        DiscountType type;
        String condition;
        BigDecimal amount;
    }
}
