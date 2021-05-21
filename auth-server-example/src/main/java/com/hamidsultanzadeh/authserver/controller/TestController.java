package com.hamidsultanzadeh.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-auth")
    public String testAuth(){
        return "Auth test";
    }

    @GetMapping("/denied")
    public String denied(){
        return "denied";
    }
}
