package com.latinhouse.backend.adapter.in.web.my;

import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.my.dto.GenerateProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.my.dto.GetProfileWebResponse;
import com.latinhouse.backend.adapter.in.web.my.dto.SetProfileWebRequest;
import com.latinhouse.backend.adapter.in.web.my.mapper.MyWebMapper;
import com.latinhouse.backend.domain.user.CustomUserDetails;
import com.latinhouse.backend.port.in.my.MyUseCase;
import com.latinhouse.backend.port.in.my.dto.AddProfileAppRequest;
import com.latinhouse.backend.port.in.my.dto.SetProfileAppRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/my")
@Tag(name = "My", description = "My API Document")
@RequiredArgsConstructor
public class ApiV1MyController {

    private final MyUseCase myUseCase;
    private final MyWebMapper myWebMapper;

    @PostMapping("profile")
    @Operation(summary = "Generate Profile", description = "Generate Profile")
    public ResponseEntity<GenerateProfileWebResponse> generateProfile(@Valid @RequestBody GenerateProfileWebRequest webReq,
                                                                      @AuthenticationPrincipal CustomUserDetails userDetails) {

        AddProfileAppRequest appReq = myWebMapper.toAppReq(webReq);
        appReq.setEmail(userDetails.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(myWebMapper.toWebRes(myUseCase.generateProfile(appReq)));
    }

    @GetMapping("profiles")
    public ResponseEntity<List<GetProfileWebResponse>> getProfiles(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                myUseCase.getProfiles(userDetails.getUsername()).stream()
                        .map(myWebMapper::toWebRes)
                        .toList()
        );
    }

    @PostMapping("/profile/assign")
    @Operation(summary = "Assign Profile", description = "Set Profile to Email")
    public ResponseEntity<Void> setProfile(@Valid @RequestBody SetProfileWebRequest webReq,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {

        SetProfileAppRequest appReq = myWebMapper.toAppReq(webReq);
        appReq.setEmail(userDetails.getUsername());
        appReq.validate();

        myUseCase.setProfile(appReq);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/instructor")
    @Operation(summary = "Enroll Instructor", description = "enroll Profile")
    public ResponseEntity<Void> setInstructor(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok().build();
    }
}
