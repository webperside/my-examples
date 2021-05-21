package com.webperside.authservice.service.inter;

import com.webperside.authservice.dto.request.RegisterUserRequestDto;
import com.webperside.authservice.models.User;

import java.util.Optional;

public interface UserService {

    void save(RegisterUserRequestDto requestDto);

    Optional<User> findByUsername(String username);

}
