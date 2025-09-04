package com.latinhouse.backend.domain.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    private DiscountType type;
    private String condition;
    private Integer amount;
}
