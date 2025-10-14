package com.latinhouse.backend.adapter.in.web.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@Tag(name = "Admin", description = "Admin API Document")
@RequiredArgsConstructor
public class ApiV1AdminController {

    @GetMapping("/hello")
    @Operation(summary = "인사하기", description = "Hello, Stranger.")
    public String hello() {
        return "Hello, stranger.";
    }

}
