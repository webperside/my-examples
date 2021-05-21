package com.webperside.authservice.service;

import com.webperside.authservice.dto.UserDto;
import com.webperside.authservice.dto.request.RegisterUserRequestDto;
import com.webperside.authservice.models.Role;
import com.webperside.authservice.models.User;
import com.webperside.authservice.models.UserRole;
import com.webperside.authservice.repository.RoleRepository;
import com.webperside.authservice.repository.UserRepository;
import com.webperside.authservice.repository.UserRoleRepository;
import com.webperside.authservice.service.inter.UserService;
import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public void save(RegisterUserRequestDto requestDto) {
        User user = User.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        userRepository.save(user);

        saveUserRoles(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void saveUserRoles(User user){
        Optional<Role> oRole = roleRepository.findByRoleNameIgnoreCase("user");

        if(oRole.isPresent()){

            UserRole userRole = UserRole.builder()
                    .role(oRole.get())
                    .user(user)
                    .build();

            System.out.println(userRole.getRole().getRoleName());

            userRoleRepository.save(userRole);
        }
    }
}
