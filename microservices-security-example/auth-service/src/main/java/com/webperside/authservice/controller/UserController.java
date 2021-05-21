package com.webperside.authservice.controller;

import com.webperside.authservice.dto.request.RegisterUserRequestDto;
import com.webperside.authservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterUserRequestDto requestDto) {
        userService.save(requestDto);
    }
}
