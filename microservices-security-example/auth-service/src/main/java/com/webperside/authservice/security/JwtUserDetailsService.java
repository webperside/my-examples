package com.webperside.authservice.security;

import com.webperside.authservice.models.User;
import com.webperside.authservice.repository.UserRepository;
import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(
                () -> ExceptionUtil.throwFor(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION)
        );
        return JwtUserFactory.create(user);
    }


}
