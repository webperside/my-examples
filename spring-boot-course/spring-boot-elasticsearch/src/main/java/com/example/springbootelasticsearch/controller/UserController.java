package com.example.springbootelasticsearch.controller;

import com.example.springbootelasticsearch.models.User;
import com.example.springbootelasticsearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
//        User user = new User();
//        user.setName("Hamid");
//        user.setSurname("Narmalni");
//        user.setBirthDate(new Date());
////
//        userRepository.save(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        System.out.println(users);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{search}")
    public ResponseEntity<List<User>> findAll(@PathVariable("search") String search){
        List<User> users = userRepository.findAllByParam(search);
        System.out.println(users);
        return ResponseEntity.ok(users);
    }

}
