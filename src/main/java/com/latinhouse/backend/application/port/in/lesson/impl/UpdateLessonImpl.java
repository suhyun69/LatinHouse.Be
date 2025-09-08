package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.LessonService;
import com.latinhouse.backend.application.port.in.lesson.UpdateLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonPortMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateLessonImpl implements UpdateLessonUseCase {

    private final LessonPortMapper lessonPortMapper;
    private final LessonService lessonService;

    @Override
    @Transactional
    public UpdateLessonAppResponse updateLesson(UpdateLessonAppRequest appReq) {
        Lesson lesson = lessonService.updateLesson(lessonPortMapper.toCommand(appReq));
        return lessonPortMapper.toAppRes(lesson, UpdateLessonAppResponse.class);
    }
}
