package com.latinhouse.backend.adapter.in.web.signup;

import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.adapter.in.web.signup.mapper.SignupWebMapper;
import com.latinhouse.backend.port.in.signup.SignupUseCase;
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
    @Operation(summary = "SignUp", description = "Add User")
    public ResponseEntity<SignupWebResponse> Signup(@Valid @RequestBody SignupWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(signupWebMapper.toWebRes(signupUseCase.addUser(signupWebMapper.toAppReq(webReq))));
    }
}
