package com.latinhouse.backend.application.lesson;

import com.latinhouse.backend.application.lesson.mapper.LessonAppMapper;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.Option;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.domain.order.service.OrderService;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.lesson.LessonUseCase;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonUseCaseImpl implements LessonUseCase {

    private final LessonAppMapper lessonAppMapper;
    private final LessonService lessonService;
    private final OrderService orderService;

    @Override
    public GetLessonAppResponse getLesson(Long lessonNo) {

        Lesson lesson = lessonService.getLesson(lessonNo)
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        return lessonAppMapper.toAppRes(lesson, GetLessonAppResponse.class);
    }

    @Override
    public ApplyLessonAppResponse applyLeson(ApplyLessonAppRequest appReq, User user) {

        Lesson lesson = lessonService.getLesson(appReq.getLessonNo())
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        lesson.getOptions().stream()
                .filter(o -> o.getSeq().equals(appReq.getLessonOptionSeq()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Option not found"));

        AddOrderCommand cmd = lessonAppMapper.toCommand(appReq);
        cmd.validate();

        Order order = orderService.create(cmd);
        return lessonAppMapper.toAppRes(lesson, ApplyLessonAppResponse.class);
    }

}
