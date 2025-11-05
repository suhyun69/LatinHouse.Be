package com.latinhouse.backend.adapter.in.web.my;

import com.latinhouse.backend.adapter.in.web.my.dto.*;
import com.latinhouse.backend.adapter.in.web.my.mapper.MyWebMapper;
import com.latinhouse.backend.domain.user.CustomUserDetails;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.EnrollInstructorAppRequest;
import com.latinhouse.backend.port.in.my.dto.AssignProfileAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/my")
@Tag(name = "My", description = "My API Document")
@RequiredArgsConstructor
public class ApiV1MyController {

    private final MyUseCase myUseCase;
    private final MyWebMapper myWebMapper;

    @PostMapping("profile")
    @Operation(summary = "Generate Profile", description = "Generate Profile")
    public ResponseEntity<GenerateProfileWebResponse> generateProfile(@Valid @RequestBody GenerateProfileWebRequest webReq,
                                                                      @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(myWebMapper.toWebRes(myUseCase.generateProfile(myWebMapper.toAppReq(webReq), userDetails.getUser())));
    }

    @GetMapping("profile")
    public ResponseEntity<GetProfileWebResponse> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(myWebMapper.toWebRes(myUseCase.getProfile(userDetails.getUser())));
    }

    @PutMapping("/profiles/{profileId}/instructor")
    @Operation(summary = "Enroll Instructor", description = "enroll Profile")
    public ResponseEntity<Void> enrollInstructor(@PathVariable("profileId") String profileId, @AuthenticationPrincipal CustomUserDetails userDetails) {

        EnrollInstructorAppRequest appReq = EnrollInstructorAppRequest.builder()
                .profileId(profileId)
                .build();
        myUseCase.enrollInstructor(appReq, userDetails.getUser());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/lesson")
    @Operation(summary = "Add Lesson", description = "Add Lesson")
    public ResponseEntity<AddLessonWebResponse> addLesson(@Valid @RequestBody AddLessonWebRequest webReq, @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(myWebMapper.toWebRes(myUseCase.addLesson(myWebMapper.toAppReq(webReq), userDetails.getUser())));
    }

    @GetMapping("/lessons")
    @Operation(summary = "Get Lessons", description = "Get All Lessons")
    public ResponseEntity<List<GetLessonWebResponse>> getLessons() {

        return ResponseEntity.ok(
                myUseCase.getLessons().stream()
                        .map(myWebMapper::toWebRes)
                        .toList()
        );
    }
}
