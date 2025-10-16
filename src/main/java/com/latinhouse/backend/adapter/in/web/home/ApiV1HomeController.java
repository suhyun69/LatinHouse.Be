package com.latinhouse.backend.adapter.in.web.home;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebRequest;
import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.adapter.in.web.home.mapper.HomeWebMapper;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebRequest;
import com.latinhouse.backend.adapter.in.web.signup.dto.SignupWebResponse;
import com.latinhouse.backend.port.in.AddTodoUseCase;
import com.latinhouse.backend.port.in.dto.AddTodoAppRequest;
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
@RequestMapping("/api/v1/home")
@Tag(name = "Home", description = "Home API Document")
@RequiredArgsConstructor
public class ApiV1HomeController {

    private final AddTodoUseCase addTodoUseCase;

    private final HomeWebMapper homeWebMapper;

    @PostMapping("/todo")
    @Operation(summary = "Add Todo", description = "Add Todo")
    public ResponseEntity<AddTodoWebResponse> Signup(@Valid @RequestBody AddTodoWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(homeWebMapper.toWebRes(addTodoUseCase.addTodo(homeWebMapper.toAppReq(webReq))));
    }
}
