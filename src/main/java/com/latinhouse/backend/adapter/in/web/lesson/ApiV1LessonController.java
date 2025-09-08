package com.latinhouse.backend.adapter.in.web.lesson;

import com.latinhouse.backend.adapter.in.web.lesson.dto.*;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.LessonWebMapper;
import com.latinhouse.backend.application.port.in.lesson.AddLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.FindLessonUseCase;
import com.latinhouse.backend.application.port.in.lesson.UpdateLessonUseCase;
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
    private final UpdateLessonUseCase updateLessonUseCase;

    private final LessonWebMapper lessonWebMapper;

    @PostMapping("")
    @Operation(summary = "Add Lesson")
    public ResponseEntity<AddLessonWebResponse> addProfile(@Valid @RequestBody AddLessonWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lessonWebMapper.toWebRes(addLessonUseCase.addLesson(lessonWebMapper.toAppReq(webReq))));
    }

    @GetMapping("")
    @Operation(summary = "Find Lessons")
    public ResponseEntity<List<LessonWebResponse>> findLessons() {

        List<LessonWebResponse> webRes = findLessonUseCase.search().stream()
                .map(lessonWebMapper::toWebRes)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(webRes);
    }

    @GetMapping("/{lessonNo}")
    @Operation(summary = "Get Lesson", description = "by lessonNo")
    public ResponseEntity<LessonWebResponse> getLesson(@PathVariable Long lessonNo) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lessonWebMapper.toWebRes(findLessonUseCase.getLesson(lessonNo)));
    }

    @PutMapping("/{lessonNo}")
    @Operation(summary = "Update Lesson")
    public ResponseEntity<UpdateLessonWebResponse> updateLesson(@PathVariable Long lessonNo, @Valid @RequestBody UpdateLessonWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lessonWebMapper.toWebRes(updateLessonUseCase.updateLesson(lessonWebMapper.toAppReq(lessonNo, webReq))));
    }
}
