package com.latinhouse.backend.domain.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private Long seq;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Region region;
    private String location;
    private String locationUrl;
    private BigDecimal price;
}