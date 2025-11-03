package com.latinhouse.backend.port.in.my.dto;

import com.latinhouse.backend.domain.lesson.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class AddLessonAppResponse {
    private Long no;
    private String title;
}
