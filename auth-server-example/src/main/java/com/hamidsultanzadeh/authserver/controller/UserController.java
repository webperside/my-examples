package com.hamidsultanzadeh.authserver.controller;

import com.hamidsultanzadeh.authserver.dto.request.RequestCreateUserDto;
import com.hamidsultanzadeh.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody RequestCreateUserDto requestCreateUserDto){
        userService.createUser(requestCreateUserDto);
    }

    @DeleteMapping
    public void deleteAll(){
        userService.deleteAll();
    }

}
