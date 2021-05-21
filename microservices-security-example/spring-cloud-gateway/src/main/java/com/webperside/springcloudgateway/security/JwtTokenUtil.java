package com.webperside.springcloudgateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.token.public-key}")
    private String publicKey;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public boolean validateToken(String token){
        boolean isValid = false;
        try{
            isValid = !isTokenExpired(token); // expired -> true, non expired -> false
        } catch (JwtException | IllegalArgumentException ignored){ }

        return isValid;

//        if(isInvalid) {
//            type.throwException();
//            // will throw special exception by type
//        }
//        return true; // token is valid
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
