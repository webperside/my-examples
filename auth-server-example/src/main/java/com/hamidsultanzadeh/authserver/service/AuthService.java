package com.hamidsultanzadeh.authserver.service;

import com.hamidsultanzadeh.authserver.dto.request.RequestLoginDto;
import com.hamidsultanzadeh.authserver.dto.response.ResponseTokenDto;
import com.hamidsultanzadeh.authserver.exception.RestException;
import com.hamidsultanzadeh.authserver.model.User;
import com.hamidsultanzadeh.authserver.model.UserLogin;
import com.hamidsultanzadeh.authserver.repository.UserLoginRepository;
import com.hamidsultanzadeh.authserver.security.JwtTokenUtil;
import com.hamidsultanzadeh.authserver.security.JwtUser;
import com.hamidsultanzadeh.authserver.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.util.DigestUtils;

import java.time.ZoneId;
import java.util.Optional;

import static com.hamidsultanzadeh.authserver.enums.ErrorEnum.BAD_CREDENTIALS_EXCEPTION;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserLoginRepository userLoginRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
//    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public ResponseTokenDto login(RequestLoginDto loginDto){
        String username = loginDto.getUsername();

        authenticate(loginDto);

        User user = userService.findByUsername(username);

        String accessToken = jwtTokenUtil.createAccessToken(user);
        String refreshToken = jwtTokenUtil.createRefreshToken(user, loginDto.isRememberMe());

        saveUserLogin(accessToken,refreshToken, user);

        return ResponseTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserLogin(String accessToken, String refreshToken, User user) {
        Optional<UserLogin> optUserLogin = userLoginRepository.findAllByUserId(user.getId()).stream().findFirst();

        UserLogin userLogin = null;
        if (optUserLogin.isPresent()){
            userLogin = optUserLogin.get();
        } else {
            userLogin = new UserLogin();
            userLogin.setUser(user);
        }

        userLogin.setAccessToken(DigestUtils.md5DigestAsHex(accessToken.getBytes()));
        userLogin.setRefreshToken(DigestUtils.md5DigestAsHex(refreshToken.getBytes()));
        userLogin.setAccessTokenExpireDate(jwtTokenUtil.getExpirationDateFromToken(accessToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime());
        userLogin.setRefreshTokenExpireDate(jwtTokenUtil.getExpirationDateFromToken(refreshToken).toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime());

        userLoginRepository.save(userLogin);
    }

    private void authenticate(RequestLoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RestException(BAD_CREDENTIALS_EXCEPTION);
        }
    }
}
