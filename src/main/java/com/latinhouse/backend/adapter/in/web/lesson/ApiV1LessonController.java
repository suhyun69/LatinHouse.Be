package com.latinhouse.backend.adapter.in.web.lesson;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebResponse;
import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lesson")
@Tag(name = "Lesson", description = "Lesson API Document")
@RequiredArgsConstructor
public class ApiV1LessonController {

    private final AddLessonUseCase addLessonUseCase;

    @PostMapping("")
    @Operation(summary = "Add Lesson")
    public ResponseEntity<AddLessonWebResponse> addProfile(@Valid @RequestBody AddLessonWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addLessonUseCase.addLesson(webReq.toAppReq()).toWebRes());
    }
}
