package com.latinhouse.backend.adapter.in.web.signin;

import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebRequest;
import com.latinhouse.backend.adapter.in.web.signin.dto.SigninWebResponse;
import com.latinhouse.backend.adapter.in.web.signin.mapper.SigninWebMapper;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.adapter.in.web.signup.mapper.SignupWebMapper;
import com.latinhouse.backend.port.in.SigninUseCase;
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
@RequestMapping("/api/v1/signin")
@Tag(name = "Signin", description = "Signin API Document")
@RequiredArgsConstructor
public class ApiV1SigninController {

    private final SigninUseCase signinUseCase;

    private final SigninWebMapper signinWebMapper;

    @PostMapping("")
    @Operation(summary = "SignIn", description = "Login")
    public ResponseEntity<SigninWebResponse> Signup(@Valid @RequestBody SigninWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(signinWebMapper.toWebRes(signinUseCase.signin(signinWebMapper.toAppReq(webReq))));
    }
}
