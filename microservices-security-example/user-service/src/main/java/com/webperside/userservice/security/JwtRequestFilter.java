package com.webperside.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String strUser = httpServletRequest.getHeader("X-User");

        if(strUser != null){
            /*
             * Əgər belə bir User varsa, gəlib bura çıxacaq. Yəni X-User dəyərinin set
             * olunması Zuul da baş verir. Əgər bu dəyər null-dırsa, deməli belə User yoxdur və ya
             * authenticated məcbur olmayan url-lərə sorğu gəlir.
             */
            JwtUser user = objectMapper.readValue(strUser, JwtUser.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
