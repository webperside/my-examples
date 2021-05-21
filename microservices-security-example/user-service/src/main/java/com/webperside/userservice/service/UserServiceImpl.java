package com.webperside.userservice.service;

import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import com.webperside.userservice.dto.response.UserDto;
import com.webperside.userservice.dto.response.UserListDto;
import com.webperside.userservice.models.User;
import com.webperside.userservice.repository.UserRepository;
import com.webperside.userservice.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserListDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserListDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            ExceptionUtil.throwFor(ErrorEnum.USER_NOT_FOUND_EXCEPTION);
        }
        return UserDto.fromEntity(user.orElse(new User()));
    }
}
