package com.latinhouse.backend.adapter.in.web.lesson;

import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.AddLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.dto.LessonWebResponse;
import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.FindLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.dto.LessonAppResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lesson")
@Tag(name = "Lesson", description = "Lesson API Document")
@RequiredArgsConstructor
public class ApiV1LessonController {

    private final AddLessonUseCase addLessonUseCase;
    private final FindLessonUseCase findLessonUseCase;

    @PostMapping("")
    @Operation(summary = "Add Lesson")
    public ResponseEntity<AddLessonWebResponse> addProfile(@Valid @RequestBody AddLessonWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addLessonUseCase.addLesson(webReq.toAppReq()).toWebRes());
    }

    @GetMapping("")
    @Operation(summary = "Find Profiles")
    public ResponseEntity<List<LessonWebResponse>> findLessons() {

        List<LessonWebResponse> webRes = findLessonUseCase.search().stream()
                .map(LessonAppResponse::toWebRes)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(webRes);
    }
}
