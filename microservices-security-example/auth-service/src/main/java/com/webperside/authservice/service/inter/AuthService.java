package com.webperside.authservice.service.inter;

import com.webperside.authservice.dto.request.GetTokenRequestDto;
import com.webperside.authservice.dto.request.RefreshTokenRequestDto;
import com.webperside.authservice.dto.response.GeneratedTokenResponseDto;

public interface AuthService {

    GeneratedTokenResponseDto getToken(GetTokenRequestDto requestDto);

    GeneratedTokenResponseDto refreshToken(RefreshTokenRequestDto requestDto);

    void logout();

}
