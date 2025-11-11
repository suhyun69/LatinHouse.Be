package com.latinhouse.backend.port.in.lesson.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplyLessonAppResponse {
    private String checkoutId;
}
