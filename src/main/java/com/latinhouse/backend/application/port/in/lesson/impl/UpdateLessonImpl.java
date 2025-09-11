package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.LessonService;
import com.latinhouse.backend.application.port.in.lesson.UpdateLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.UpdateLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonPortMapper;
import com.latinhouse.backend.common.exception.LessonNotFoundException;
import com.latinhouse.backend.common.exception.ProfileNotFoundException;
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

        Lesson asIs = lessonService.getLesson(appReq.getNo())
                .orElseThrow(() -> new LessonNotFoundException(appReq.getNo()));

        Lesson toBe = lessonService.updateLesson(asIs, lessonPortMapper.toCommand(appReq));
        return lessonPortMapper.toAppRes(toBe, UpdateLessonAppResponse.class);
    }
}
