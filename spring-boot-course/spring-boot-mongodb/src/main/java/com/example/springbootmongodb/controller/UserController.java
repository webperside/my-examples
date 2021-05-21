package com.example.springbootmongodb.controller;

import com.example.springbootmongodb.models.User;
import com.example.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init(){
//        HashMap<String, Object> additionalInfo = new HashMap<>();
//
//        additionalInfo.put("age", 12);
//        additionalInfo.put("address","Yeni Surakhani");
//
//        User user = new User();
//        user.setName("Hamid");
//        user.setSurname("Sultanzadeh");
//        user.setAdditionalInfo(additionalInfo);
//
//        userRepository.save(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(User user){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
