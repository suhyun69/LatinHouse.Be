package com.latinhouse.backend.adapter.in.web.lesson.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyLessonWebRequest {

    @NotNull(message = "lessonNo cannot be null.")
    private Long lessonNo;

    @NotNull(message = "lessonOptionSeq cannot be null.")
    private Long lessonOptionSeq;
}
