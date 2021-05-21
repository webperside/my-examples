package com.webperside.courseservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webperside.commonservice.dto.ErrorDto;
import com.webperside.commonservice.enums.ErrorEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ErrorEnum errorEnum = ErrorEnum.ACCESS_TOKEN_IS_EXPIRED_EXCEPTION;

        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatus(errorEnum.getStatus().value());
        errorDto.setErrorCode(errorEnum.getErrorCode());
        errorDto.setErrorMessage(errorEnum.getErrorMessage());
        errorDto.setTimestamp(new Date());

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON);
        httpServletResponse.setStatus(errorEnum.getStatus().value());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorDto));
    }
}
