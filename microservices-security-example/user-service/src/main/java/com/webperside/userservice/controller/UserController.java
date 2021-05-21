package com.webperside.userservice.controller;

import com.webperside.userservice.dto.response.UserDto;
import com.webperside.userservice.dto.response.UserListDto;
import com.webperside.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public List<UserListDto> users(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto byId(@PathVariable("userId") Integer userId){
        return userService.findById(userId);
    }
}
