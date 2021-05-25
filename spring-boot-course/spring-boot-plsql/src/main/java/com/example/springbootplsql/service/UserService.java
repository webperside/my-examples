package com.example.springbootplsql.service;

import com.example.springbootplsql.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto userDto);

    void delete(UserDto userDto);

    UserDto findById(Long id);

    Page<UserDto> findAll(Pageable pageable);
}
