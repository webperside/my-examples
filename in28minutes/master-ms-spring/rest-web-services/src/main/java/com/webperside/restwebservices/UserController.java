package com.webperside.restwebservices;

import com.webperside.restwebservices.models.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"User1",new Date()));
        users.add(new User(2,"User2",new Date()));
        users.add(new User(3,"User3",new Date()));
    }

    @GetMapping
    public List<User> getUsers(){
        return users;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable("id") Integer id){
        User user = users.get(0);

        for(User u : users){
            if(u.getId().equals(id)){
                user = u;
            }
        }

        EntityModel<User> model = EntityModel.of(user);

        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());

        model.add(linkToUsers.withRel("all-users"));

        return model;
    }
}
