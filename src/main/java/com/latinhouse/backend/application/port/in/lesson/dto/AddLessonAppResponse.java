package com.latinhouse.backend.application.port.in.lesson.dto;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.profile.dto.AddProfileWebResponse;
import com.latinhouse.backend.domain.lesson.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddLessonAppResponse {
    private Long no;

    public static AddLessonAppResponse from(Lesson lesson) {
        return AddLessonAppResponse.builder()
                .no(lesson.getNo())
                .build();
    }

    public AddLessonWebResponse toWebRes() {
        return AddLessonWebResponse.builder()
                .no(this.no)
                .build();
    }
}
