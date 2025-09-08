package com.latinhouse.backend.application.port.in.lesson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateLessonAppResponse {
    private Long no;
}
