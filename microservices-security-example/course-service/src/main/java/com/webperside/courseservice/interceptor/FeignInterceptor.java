package com.webperside.courseservice.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            requestTemplate.header("X-User", objectMapper.writeValueAsString(principal));
        }
    }
}
