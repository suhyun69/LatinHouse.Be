package com.latinhouse.backend.adapter.in.web.lesson;

import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebRequest;
import com.latinhouse.backend.adapter.in.web.lesson.dto.ApplyLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.dto.GetLessonWebResponse;
import com.latinhouse.backend.adapter.in.web.lesson.mapper.LessonWebMapper;
import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebRequest;
import com.latinhouse.backend.domain.user.CustomUserDetails;
import com.latinhouse.backend.port.in.lesson.LessonUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lessons")
@Tag(name = "Lesson", description = "Lesson API Document")
@RequiredArgsConstructor
public class ApiV1LessonController {

    private final LessonUseCase lessonUseCase;
    private final LessonWebMapper lessonWebMapper;

    @GetMapping("/{lessonNo}")
    public ResponseEntity<GetLessonWebResponse> getLesson(@PathVariable("lessonNo") Long lessonNo) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonWebMapper.toWebRes(lessonUseCase.getLesson(lessonNo)));
    }

    @PostMapping("/apply")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApplyLessonWebResponse> applyLesson(@Valid @RequestBody ApplyLessonWebRequest webReq, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(lessonWebMapper.toWebRes(lessonUseCase.applyLeson(lessonWebMapper.toAppReq(webReq), userDetails.getUser())));
    }
}
