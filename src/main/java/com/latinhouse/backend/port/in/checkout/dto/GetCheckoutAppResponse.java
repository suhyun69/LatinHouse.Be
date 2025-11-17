package com.latinhouse.backend.port.in.checkout.dto;

import com.latinhouse.backend.adapter.in.web.checkout.dto.GetCheckoutWebResponse;
import com.latinhouse.backend.domain.lesson.DiscountType;
import com.latinhouse.backend.domain.lesson.Genre;
import com.latinhouse.backend.domain.lesson.Region;
import com.latinhouse.backend.domain.order.OrderStatus;
import com.latinhouse.backend.domain.profile.Profile;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetCheckoutAppResponse {
    private String checkoutId;
    private OrderStatus status;

    private String lessonTitle;
    private Long lessonNo;
    private Genre genre;
    private String instructorLoNickname;
    private String instructorLoId;
    private String instructorLaNickname;
    private String instructorLaId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Region region;
    private String location;
    private BigDecimal price;
    private List<Discount>  discounts;

    @Data
    @Builder
    public static class Discount {
        Long no;
        DiscountType type;
        String condition;
        BigDecimal amount;
    }
}
