package com.latinhouse.backend.adapter.in.web.lesson;

import com.latinhouse.backend.adapter.in.web.lesson.dto.GetLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.LessonWebMapper;
import com.latinhouse.backend.port.in.lesson.LessonUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lesson")
@Tag(name = "Lesson", description = "Lesson API Document")
@RequiredArgsConstructor
public class ApiV1LessonController {

    private final LessonUseCase lessonUseCase;
    private final LessonWebMapper lessonWebMapper;

    @GetMapping("/lessons/{no}")
    public ResponseEntity<GetLessonWebResponse> getLesson(@PathVariable("no") Long no) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonWebMapper.toWebRes(lessonUseCase.getLesson(no)));
    }
}
