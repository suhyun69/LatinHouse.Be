package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.adapter.in.web.dto.AddProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.dto.AddProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.dto.ProfileWebResponse;
import com.latinhouse.backend.application.port.in.*;
import com.latinhouse.backend.application.port.in.dto.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.dto.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.dto.ProfileAppResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@Tag(name = "Profile", description = "Profile API Document")
@RequiredArgsConstructor
public class ApiV1ProfileController {

    private final SignupUseCase signupUseCase;
    private final FindProfileUseCase findProfileUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;

    @PostMapping("")
    @Operation(summary = "Add Profile", description = "by email")
    public ResponseEntity<AddProfileWebResponse> addProfile(@Valid @RequestBody AddProfileWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupUseCase.addByEmail(webReq.toAppReq()).toWebRes());
    }

    @GetMapping("")
    @Operation(summary = "Find Profiles")
    public ResponseEntity<List<ProfileWebResponse>> findProfiles() {

        List<ProfileWebResponse> webRes = findProfileUseCase.search().stream()
                .map(ProfileAppResponse::toWebRes)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(webRes);
    }

    @GetMapping("/{profileId}")
    @Operation(summary = "Get Profile", description = "by profileId")
    public ResponseEntity<ProfileWebResponse> getProfile(@PathVariable String profileId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(findProfileUseCase.getProfile(profileId).toWebRes());
    }

    @PutMapping("/instructor/{profileId}")
    @Operation(summary = "Enroll Instructor", description = "True only")
    public ResponseEntity<Void> enrollInstructor(@PathVariable String profileId) {

        updateProfileUseCase.enrollInstructor(profileId);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
