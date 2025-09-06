package com.latinhouse.backend.application.port.in.lesson.impl;

import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppRequest;
import com.latinhouse.backend.application.port.in.lesson.dto.AddLessonAppResponse;
import com.latinhouse.backend.application.port.in.lesson.mapper.LessonMapper;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddLessonImpl implements AddLessonUseCase {

    private final LessonMapper lessonMapper;
    private final LessonService lessonService;

    @Override
    @Transactional
    public AddLessonAppResponse addLesson(AddLessonAppRequest appReq) {
        Lesson lesson = lessonService.addLesson(lessonMapper.toCommand(appReq));
        return lessonMapper.toAddLessonAppRes(lesson);
    }
}
