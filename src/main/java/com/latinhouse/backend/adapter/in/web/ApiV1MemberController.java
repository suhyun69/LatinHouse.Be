package com.latinhouse.backend.adapter.in.web;

import com.latinhouse.backend.application.port.in.AddMemberAppRequest;
import com.latinhouse.backend.application.port.in.AddMemberAppResponse;
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
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "Member API Document")
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final SignupUseCase signupUseCase;

    @PostMapping("")
    @Operation(summary = "Add Member", description = "by email")
    public ResponseEntity<AddMemberWebResponse> addMember(@Valid @RequestBody AddMemberWebRequest webReq) {

        AddMemberAppRequest appReq = AddMemberAppRequest.from(webReq);
        AddMemberAppResponse appRes = signupUseCase.addByEmail(appReq);
        AddMemberWebResponse webRes = AddMemberWebResponse.from(appRes);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(webRes);
    }
}