package com.latinhouse.backend.application.lesson;

import com.latinhouse.backend.application.lesson.mapper.LessonAppMapper;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.port.in.lesson.LessonUseCase;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonUseCaseImpl implements LessonUseCase {

    private final LessonAppMapper lessonAppMapper;
    private final LessonService lessonService;

    @Override
    public GetLessonAppResponse getLesson(Long no) {

        Lesson lesson = lessonService.getLesson(no)
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        return lessonAppMapper.toAppRes(lesson, GetLessonAppResponse.class);
    }
}
