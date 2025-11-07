package com.latinhouse.backend.port.in.lesson.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ApplyLessonAppRequest extends SelfValidating<ApplyLessonAppRequest> {

    @NotNull(message = "lessonNo cannot be null.")
    Long lessonNo;

    @NotNull(message = "lessonOptionSeq cannot be null.")
    Long lessonOptionSeq;

    @Builder
    public ApplyLessonAppRequest(Long lessonNo, Long lessonOptionSeq) {
        this.lessonNo = lessonNo;
        this.lessonOptionSeq = lessonOptionSeq;

        this.validateSelf();
    }
}
