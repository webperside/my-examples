package com.webperside.userservice.service.inter;

import com.webperside.userservice.dto.response.UserDto;
import com.webperside.userservice.dto.response.UserListDto;

import java.util.List;

public interface UserService {

    List<UserListDto> findAll();

    UserDto findById(Integer id);

}
