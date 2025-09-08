package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonPortMapper;
import com.latinhouse.backend.application.domain.lesson.Lesson;
import com.latinhouse.backend.application.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddLessonImpl implements AddLessonUseCase {

    private final LessonPortMapper lessonPortMapper;
    private final LessonService lessonService;

    @Override
    @Transactional
    public AddLessonAppResponse addLesson(AddLessonAppRequest appReq) {
        Lesson lesson = lessonService.addLesson(lessonPortMapper.toCommand(appReq));
        return lessonPortMapper.toAppRes(lesson, AddLessonAppResponse.class);
    }
}
