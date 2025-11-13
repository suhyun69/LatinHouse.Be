package com.latinhouse.backend.port.in.lesson.dto;

import com.latinhouse.backend.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class ApplyLessonAppRequest extends SelfValidating<ApplyLessonAppRequest> {

    @NotNull(message = "lessonOptionNo cannot be null.")
    Long lessonOptionNo;

    @Builder
    public ApplyLessonAppRequest(Long lessonOptionNo) {
        this.lessonOptionNo = lessonOptionNo;

        this.validateSelf();
    }
}
