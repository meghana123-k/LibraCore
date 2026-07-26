package com.libracore.controller;

import com.libracore.dto.AuthResponseDTO;
import com.libracore.dto.LoginRequestDTO;
import com.libracore.dto.RegisterRequestDTO;
import com.libracore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO dto) {

        return authService.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @Valid @RequestBody LoginRequestDTO dto) {

        return authService.login(dto);
    }
}