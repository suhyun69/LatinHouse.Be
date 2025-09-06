package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.FindLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.application.port.in.profile.dto.ProfileAppResponse;
import com.latinhouse.backend.domain.lesson.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLessonImpl implements FindLessonUseCase {

    private final LessonService lessonService;

    @Override
    public List<LessonAppResponse> search() {
        return lessonService.search().stream()
                .map(LessonAppResponse::from)
                .toList();
    }
}
