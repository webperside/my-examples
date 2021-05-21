package com.hamidsultanzadeh.authserver.security;

import com.hamidsultanzadeh.authserver.model.User;
import com.hamidsultanzadeh.authserver.repository.UserLoginRepository;
import com.hamidsultanzadeh.authserver.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final UserRepository userRepository;
    private final UserLoginRepository userLoginRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String token = httpServletRequest.getHeader("Authorization");

        if(token != null && jwtTokenUtil.validateToken(token)){

            Optional<User> optUser = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
            if(optUser.isPresent()){
                if (checkAccessTokenIsExist(token, optUser.get())) {
                    Authentication auth = jwtTokenUtil.getAuthentication(token);

                    if(auth != null) SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean checkAccessTokenIsExist(String token, User user) {
        return userLoginRepository.findAllByUserId(user.getId()).stream().anyMatch(userLogin ->
            userLogin.getAccessToken().equals(DigestUtils.md5DigestAsHex(token.getBytes()))
        );
    }
}
