package com.webperside.authservice.service;

import com.webperside.authservice.util.UserUtils;
import com.webperside.authservice.dto.request.GetTokenRequestDto;
import com.webperside.authservice.dto.request.RefreshTokenRequestDto;
import com.webperside.authservice.dto.response.GeneratedTokenResponseDto;
import com.webperside.authservice.models.User;
import com.webperside.authservice.models.UserAuthorization;
import com.webperside.authservice.repository.UserAuthorizationRepository;
import com.webperside.authservice.security.JwtTokenUtil;
import com.webperside.authservice.service.inter.AuthService;
import com.webperside.authservice.service.inter.UserService;
import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.token.access-token-validity-time}")
    private long accessTokenValidityTime;

    @Value("${jwt.token.refresh-token-validity-time}")
    private long refreshTokenValidityTime;

    private final UserService userService;
    private final UserAuthorizationRepository userAuthorizationRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenUtil;

    @Override
    public GeneratedTokenResponseDto getToken(GetTokenRequestDto requestDto) {
        User user = retrieveUserByUsername(requestDto.getUsername()); // if user does not exist, will throw exception

        authenticate(requestDto); // checking for password is correct

        return generateTokenAndSave(user, requestDto.isRememberMe()); // generate token and save as UserAuthorization
    }

    @Override
    public GeneratedTokenResponseDto refreshToken(RefreshTokenRequestDto requestDto) {
        userAuthorizationRepository.findByRefreshToken(
                DigestUtils.md5DigestAsHex(requestDto.getRefreshToken().getBytes())
        ).orElseThrow(() -> ExceptionUtil.throwFor(ErrorEnum.REFRESH_TOKEN_IS_EXPIRED_EXCEPTION));

        if(!tokenUtil.validateToken(requestDto.getRefreshToken(), JwtTokenUtil.ValidateType.REFRESH_TOKEN)){
            String username = tokenUtil.getUsernameFromToken(requestDto.getRefreshToken());
            User user = retrieveUserByUsername(username); // if user does not exist, will throw exception

            return generateTokenAndSave(user, requestDto.isRememberMe());
        }
        return null;
    }

    @Override
    public void logout() {
        UserAuthorization authorization = userAuthorizationRepository.findByUserId(UserUtils.CURRENT_USER_ID()).orElse(null);

        if(authorization == null) return;

        userAuthorizationRepository.delete(authorization);
    }

    private void authenticate(GetTokenRequestDto request){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            ExceptionUtil.throwFor(ErrorEnum.PASSWORD_DOES_NOT_MATCH_EXCEPTION);
        }

    }

    private User retrieveUserByUsername(String username){
        return userService.findByUsername(username).orElseThrow(
                () -> ExceptionUtil.throwFor(ErrorEnum.USERNAME_NOT_FOUND_EXCEPTION)
        );
    }

    private GeneratedTokenResponseDto generateTokenAndSave(User user, boolean isRememberMe){
        String accessToken = tokenUtil.createAccessToken(user);
        String refreshToken = tokenUtil.createRefreshToken(user, isRememberMe);

        saveUserAuthorization(user, accessToken, refreshToken, isRememberMe);

        return GeneratedTokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserAuthorization(User user, String accessToken, String refreshToken, boolean isRememberMe){
        Optional<UserAuthorization> oUserAuthorization = userAuthorizationRepository.findByUserId(user.getId());
        UserAuthorization authorization;

        if(oUserAuthorization.isEmpty()){
            authorization = new UserAuthorization();
            authorization.setUser(user);
        } else {
            authorization = oUserAuthorization.get();
        }

        authorization.setAccessToken(DigestUtils.md5DigestAsHex(accessToken.getBytes()));
        authorization.setAccessTokenExpireDate(LocalDateTime.now().plus(accessTokenValidityTime, ChronoUnit.MILLIS));
        authorization.setRefreshToken(DigestUtils.md5DigestAsHex(refreshToken.getBytes()));
        authorization.setRefreshTokenExpireDate(LocalDateTime.now()
                .plus(refreshTokenValidityTime * (isRememberMe ? 30 : 1), ChronoUnit.MILLIS)
        );

        userAuthorizationRepository.save(authorization);
    }
}
