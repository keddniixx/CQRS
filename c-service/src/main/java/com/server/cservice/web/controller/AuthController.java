package com.server.cservice.web.controller;

import com.server.cservice.domain.dto.ClientDto;
import com.server.cservice.service.auth.AuthService;
import com.server.cservice.web.dto.auth.LoginRequestDto;
import com.server.cservice.web.dto.auth.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Validated @RequestBody ClientDto clientDto) {
        authService.register(clientDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Validated @RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

}
