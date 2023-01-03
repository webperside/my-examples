package com.webperside.argumentcaptor.service.functional;

import com.webperside.argumentcaptor.model.enums.UserStatus;
import com.webperside.argumentcaptor.model.mybatis.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User findById(long id) {
        return new User(id, "example@webperside.com", "123", UserStatus.ACTIVE);
    }

    public void save(User user) {

    }

}
