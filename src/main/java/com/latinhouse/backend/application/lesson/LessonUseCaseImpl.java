package com.latinhouse.backend.application.lesson;

import com.latinhouse.backend.application.lesson.mapper.LessonAppMapper;
import com.latinhouse.backend.common.exception.NotFoundException;
import com.latinhouse.backend.domain.lesson.Lesson;
import com.latinhouse.backend.domain.lesson.service.LessonService;
import com.latinhouse.backend.domain.order.Order;
import com.latinhouse.backend.domain.order.OrderStatus;
import com.latinhouse.backend.domain.order.command.AddOrderCommand;
import com.latinhouse.backend.domain.order.service.OrderService;
import com.latinhouse.backend.domain.user.User;
import com.latinhouse.backend.port.in.lesson.LessonUseCase;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppRequest;
import com.latinhouse.backend.port.in.lesson.dto.ApplyLessonAppResponse;
import com.latinhouse.backend.port.in.lesson.dto.GetLessonAppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonUseCaseImpl implements LessonUseCase {

    private final LessonAppMapper lessonAppMapper;
    private final LessonService lessonService;
    private final OrderService orderService;

    @Override
    public GetLessonAppResponse getLesson(Long no) {

        Lesson lesson = lessonService.getLesson(no)
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        return lessonAppMapper.toAppRes(lesson, GetLessonAppResponse.class);
    }

    @Override
    public ApplyLessonAppResponse applyLeson(ApplyLessonAppRequest appReq, User user) {

        Lesson lesson = lessonService.getLessonByOption(appReq.getLessonOptionSeq())
                .orElseThrow(() -> new NotFoundException("Lesson not found"));

        AddOrderCommand cmd = lessonAppMapper.toCommand(appReq);
        cmd.setId(UUID.randomUUID().toString());
        cmd.setLessonNo(lesson.getNo());
        cmd.setStatus(OrderStatus.PAYMENT_PENDING);
        cmd.validate();

        Order order = orderService.create(cmd);
        return lessonAppMapper.toAppRes(order, ApplyLessonAppResponse.class);
    }
}
