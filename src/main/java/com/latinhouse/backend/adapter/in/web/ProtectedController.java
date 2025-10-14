package com.latinhouse.backend.adapter.in.web;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/me")
    public String me() {
        return "This is protected resource.";
    }
}