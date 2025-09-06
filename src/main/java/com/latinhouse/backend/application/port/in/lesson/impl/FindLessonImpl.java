package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.FindLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonMapper;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLessonImpl implements FindLessonUseCase {

    private final LessonMapper lessonMapper;
    private final LessonService lessonService;

    @Override
    public List<LessonAppResponse> search() {
        return lessonService.search().stream()
                .map(lessonMapper::toAppRes)
                .toList();
    }
}
