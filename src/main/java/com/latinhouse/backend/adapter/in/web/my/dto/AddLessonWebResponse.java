package com.latinhouse.backend.adapter.in.web.my.dto;

import com.latinhouse.backend.domain.lesson.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class AddLessonWebResponse {
    private Long no;
    private String title;
}
