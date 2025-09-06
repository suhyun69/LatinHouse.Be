package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.FindLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonPortMapper;
import com.latinhouse.backend.common.exception.LessonNotFoundException;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindLessonImpl implements FindLessonUseCase {

    private final LessonPortMapper lessonPortMapper;
    private final LessonService lessonService;

    @Override
    public List<LessonAppResponse> search() {
        return lessonService.search().stream()
                .map(lesson -> lessonPortMapper.toAppRes(lesson, LessonAppResponse.class))
                .toList();
    }

    @Override
    public LessonAppResponse getLesson(Long LessonNo) {
        Lesson lesson = lessonService.getLesson(LessonNo)
                .orElseThrow(() -> new LessonNotFoundException(LessonNo));

        return lessonPortMapper.toAppRes(lesson, LessonAppResponse.class);
    }
}
