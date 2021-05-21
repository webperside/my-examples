package com.hamidsultanzadeh.authserver.security;

import com.hamidsultanzadeh.authserver.model.Role;
import com.hamidsultanzadeh.authserver.model.User;
import com.hamidsultanzadeh.authserver.model.UserRole;
import com.hamidsultanzadeh.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException(String.format("username %s not found",s)));
        return JwtUser.builder()
                .username(s)
                .password(user.getPassword())
                .authorities(user.getRoles() != null ?
                        mapRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList())) :
                        null)
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRoles(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
