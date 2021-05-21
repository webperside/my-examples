package com.webperside.authservice.security;

import com.webperside.authservice.models.Role;
import com.webperside.authservice.models.User;
import com.webperside.authservice.models.UserRole;
import com.webperside.commonservice.enums.ErrorEnum;
import com.webperside.commonservice.exception.ExceptionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    public enum ValidateType{
        ACCESS_TOKEN(ErrorEnum.ACCESS_TOKEN_IS_EXPIRED_EXCEPTION),
        REFRESH_TOKEN(ErrorEnum.REFRESH_TOKEN_IS_EXPIRED_EXCEPTION);

        private final ErrorEnum willThrow;

        ValidateType(ErrorEnum willThrow) {
            this.willThrow = willThrow;
        }

        public void throwException(){
            ExceptionUtil.throwFor(this.getWillThrow());
        }

        public ErrorEnum getWillThrow() {
            return willThrow;
        }
    }

    @Value("${jwt.token.private-key}")
    private String privateKey;

    @Value("${jwt.token.public-key}")
    private String publicKey;

    @Value("${jwt.token.access-token-validity-time}")
    private long accessTokenValidityTime;

    @Value("${jwt.token.refresh-token-validity-time}")
    private long refreshTokenValidityTime;

    private final UserDetailsService jwtUserDetailsService;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String createAccessToken(User user){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles",getRoleNames(user.getRoles().stream()
                .map(UserRole::getRole).collect(Collectors.toList())));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    public String createRefreshToken(User user, boolean rememberMe){
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles","REFRESH_TOKEN");

        Date now = new Date();
        Date expiration = null;

        if(rememberMe) expiration = new Date(now.getTime() + refreshTokenValidityTime * 30);
        else expiration = new Date(now.getTime() + refreshTokenValidityTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    public boolean validateToken(String token, ValidateType type){
        boolean isInvalid = false;
        try{
            isInvalid = isTokenExpired(token); // expired -> true, non expired -> false
        } catch (JwtException | IllegalArgumentException e){
            type.throwException();
        }

        if(isInvalid) type.throwException(); // will throw special exception by type
        return true; // token is valid
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    // private util methods

    private boolean isTokenExpired(String token){
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private List<String> getRoleNames(List<Role> roles){
        return roles.stream().map(Role::getRoleName).collect(Collectors.toList());
    }

    private PrivateKey getPrivateKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return kf.generatePrivate(keySpecPKCS8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private PublicKey getPublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
    }
}
