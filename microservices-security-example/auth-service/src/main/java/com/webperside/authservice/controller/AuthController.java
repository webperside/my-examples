package com.webperside.authservice.controller;

import com.webperside.authservice.dto.request.GetTokenRequestDto;
import com.webperside.authservice.dto.request.RefreshTokenRequestDto;
import com.webperside.authservice.dto.response.GeneratedTokenResponseDto;
import com.webperside.authservice.service.inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/get-token")
    public GeneratedTokenResponseDto getToken(@RequestBody GetTokenRequestDto requestDto){

        return authService.getToken(requestDto);
    }

    @PostMapping("/refresh-token")
    public GeneratedTokenResponseDto refreshToken(@RequestBody RefreshTokenRequestDto requestDto){
        return authService.refreshToken(requestDto);
    }

    @GetMapping("/log-out")
    public void logout(){
        authService.logout();
    }
}
