package com.example.springbootplsql.controller;

import com.example.springbootplsql.dto.UserDto;
import com.example.springbootplsql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
        userDto = userService.save(userDto);
        return ResponseEntity.ok(userDto);
    }




}
