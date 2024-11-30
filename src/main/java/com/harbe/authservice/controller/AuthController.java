package com.harbe.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.harbe.authservice.dto.message.RegisterDto;
import com.harbe.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth", description = "REST APIs for Auth")
@RestController
// @RequestMapping("/api/auth")
@RequiredArgsConstructor // for final hoặc @NonNull
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register API", description = "Register a new user, can be used with both '/register' and '/signup' endpoints")

    @ApiResponse(responseCode = "200", description = "HTTP Status 200 OK")

    // ResponseEntity: represents an HTTP response, including headers, body, and
    // status
    @PostMapping(value = { "/register", "/signup" })
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto) {
        return new ResponseEntity<>(this.authService.register(registerDto), HttpStatus.CREATED);
    }
}
