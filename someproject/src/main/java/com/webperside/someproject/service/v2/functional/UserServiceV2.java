package com.webperside.someproject.service.v2.functional;

import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
