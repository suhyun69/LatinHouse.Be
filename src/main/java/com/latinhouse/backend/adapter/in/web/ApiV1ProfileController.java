package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.application.port.in.AddProfileAppRequest;
import com.latinhouse.backend.application.port.in.AddProfileAppResponse;
import com.latinhouse.backend.application.port.in.SignupUseCase;
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
@RequestMapping("/api/v1/profile")
@Tag(name = "Profile", description = "Profile API Document")
@RequiredArgsConstructor
public class ApiV1ProfileController {

    private final SignupUseCase signupUseCase;

    @PostMapping("")
    @Operation(summary = "Add Profile", description = "by email")
    public ResponseEntity<AddProfileWebResponse> addProfile(@Valid @RequestBody AddProfileWebRequest webReq) {

        AddProfileAppRequest appReq = AddProfileAppRequest.from(webReq);
        AddProfileAppResponse appRes = signupUseCase.addByEmail(appReq);
        AddProfileWebResponse webRes = AddProfileWebResponse.from(appRes);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(webRes);
    }
}
