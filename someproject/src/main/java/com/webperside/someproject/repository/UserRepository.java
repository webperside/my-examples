package com.webperside.someproject.repository;

import com.webperside.someproject.model.enums.UserStatus;
import com.webperside.someproject.model.mybatis.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    public long save(User user ){
        return 1L;
    }

    public Optional<User> findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setFirstName("Name");
        user.setLastName("Surname");
        user.setUserStatus(UserStatus.APPROVED);
        return Optional.of(user);
    }

    public void updateStatus(Long id, UserStatus status) {

    }

}
