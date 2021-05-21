package com.hamidsultanzadeh.authserver.service;

import com.hamidsultanzadeh.authserver.dto.request.RequestCreateUserDto;
import com.hamidsultanzadeh.authserver.enums.ErrorEnum;
import com.hamidsultanzadeh.authserver.exception.RestException;
import com.hamidsultanzadeh.authserver.model.Role;
import com.hamidsultanzadeh.authserver.model.User;
import com.hamidsultanzadeh.authserver.model.UserRole;
import com.hamidsultanzadeh.authserver.repository.UserLoginRepository;
import com.hamidsultanzadeh.authserver.repository.UserRepository;
import com.hamidsultanzadeh.authserver.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(RequestCreateUserDto requestCreateUserDto){
        User user = User.builder()
                .firstName(requestCreateUserDto.getFirstName())
                .lastName(requestCreateUserDto.getLastName())
                .username(requestCreateUserDto.getUsername())
                .password(passwordEncoder.encode(requestCreateUserDto.getPassword()))
                .build();

        userRepository.save(user);

        Role role = new Role();
        role.setId(1);

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        userRoleRepository.save(userRole);
    }

    @Transactional
    public void deleteAll(){
        userLoginRepository.deleteAll();
        userRepository.deleteAll();
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RestException(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION,
                        String.format("username: %s",username)));
    }
}
