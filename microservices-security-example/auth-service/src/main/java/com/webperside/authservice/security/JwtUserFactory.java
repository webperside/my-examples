package com.webperside.authservice.security;

import com.webperside.authservice.models.Role;
import com.webperside.authservice.models.User;
import com.webperside.authservice.models.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser create(User user){
        return JwtUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .id(user.getId())
                .authorities(user.getRoles() != null ?
                        mapToGrantedAuthorities(user.getRoles().stream()
                                .map(UserRole::getRole)
                                .collect(Collectors.toList()))
                        : null
                )
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

}
