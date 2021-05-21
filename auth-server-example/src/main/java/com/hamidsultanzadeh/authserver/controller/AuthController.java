package com.hamidsultanzadeh.authserver.controller;

import com.hamidsultanzadeh.authserver.dto.request.RequestLoginDto;
import com.hamidsultanzadeh.authserver.dto.response.ResponseTokenDto;
import com.hamidsultanzadeh.authserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseTokenDto login(@RequestBody RequestLoginDto loginDto){
        return authService.login(loginDto);
    }
}
