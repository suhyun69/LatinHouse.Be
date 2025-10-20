package com.latinhouse.backend.adapter.in.web.home;

import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebRequest;
import com.latinhouse.backend.adapter.in.web.home.dto.AddTodoWebResponse;
import com.latinhouse.backend.adapter.in.web.home.dto.GetTodoWebResponse;
import com.latinhouse.backend.adapter.in.web.home.mapper.HomeWebMapper;
import com.latinhouse.backend.port.in.home.HomeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/home")
@Tag(name = "Home", description = "Home API Document")
@RequiredArgsConstructor
public class ApiV1HomeController {

    private final HomeUseCase homeUseCase;

    private final HomeWebMapper homeWebMapper;

    @PostMapping("/todo")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Add Todo", description = "Add Todo")
    public ResponseEntity<AddTodoWebResponse> addTodo(@Valid @RequestBody AddTodoWebRequest webReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(homeWebMapper.toWebRes(homeUseCase.addTodo(homeWebMapper.toAppReq(webReq))));
    }

    @GetMapping("/todos")
    @Operation(summary = "Get Todos", description = "Get Todos")
    public ResponseEntity<List<GetTodoWebResponse>> getTodos() {

        return ResponseEntity.ok(
                homeUseCase.getTodos().stream()
                        .map(homeWebMapper::toWebRes)
                        .toList()
        );
    }

    @PutMapping("/done/{no}")
    @Operation(summary = "Get Todos", description = "Get Todos")
    public ResponseEntity<GetTodoWebResponse> getTodos(@PathVariable Long no) {

        return ResponseEntity.ok(homeWebMapper.toWebRes(homeUseCase.done(no)));
    }
}
