package com.digitalskies.demo.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey secretKey;

    public final long ACCESS_TOKEN_VALIDITY = 15 * 60 * 60 * 1000L;


    public final long REFRESH_TOKEN_VALIDITY =30 * 24 * 60 * 60 * 60 * 1000L;


    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
    }



    private String generateToken(
       String userId,
       String type,
       Long expiry
    ){
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(userId)
                .claim("type",type)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiry))
                .signWith(secretKey,Jwts.SIG.HS256)
                .compact();


    }

    public String generateAccessToken(String userId){
        return generateToken(userId,"access",ACCESS_TOKEN_VALIDITY);

    }

    public String generateRefreshToken(String userId){
        return generateToken(userId,"refresh",REFRESH_TOKEN_VALIDITY);
    }

    public Boolean validateAccessToken(String rawToken){

        var claims=parseJWTToken(rawToken);

        if(claims==null){
            return false;
        }

        var tokenType = claims.get("type");

        if(tokenType==null){
            return false;
        }

        return ((String)tokenType).equalsIgnoreCase("access");

    }

    public Boolean validateRefreshToken(String rawToken){

        var claims=parseJWTToken(rawToken);

        if(claims==null){
            return false;
        }

        var tokenType = claims.get("type");

        if(tokenType==null){
            return false;
        }

        return ((String)tokenType).equalsIgnoreCase("refresh");

    }

    public String getUsernameFromToken(String token){
        var claims=parseJWTToken(token);

        if(claims==null){
            throw new IllegalArgumentException("Invalid token");
        }
        return claims.getSubject();
    }

    private Claims parseJWTToken(String token){
        String rawToken = token.replaceFirst("^Bearer ","");

        try {
            return Jwts.parser().verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(rawToken)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }

    }


}
