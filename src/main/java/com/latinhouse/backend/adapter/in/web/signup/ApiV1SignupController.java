package com.latinhouse.backend.adapter.in.web.signup;

import com.latinhouse.backend.adapter.in.web.signup.dto.AddUserWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.AddUserWebResponse;
import com.latinhouse.backend.adapter.in.web.signup.mapper.SignupWebMapper;
import com.latinhouse.backend.port.in.SignupUseCase;
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
@RequestMapping("/api/v1/signup")
@Tag(name = "Signup", description = "Signup API Document")
@RequiredArgsConstructor
public class ApiV1SignupController {

    private final SignupUseCase signupUseCase;

    private final SignupWebMapper signupWebMapper;

    @PostMapping("")
    @Operation(summary = "Add User", description = "by email")
    public ResponseEntity<AddUserWebResponse> addUser(@Valid @RequestBody AddUserWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupWebMapper.toWebRes(signupUseCase.addUser(signupWebMapper.toAppReq(webReq))));
    }
}
