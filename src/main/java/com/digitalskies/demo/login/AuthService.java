package com.digitalskies.demo.login;


import com.digitalskies.demo.security.HashEncoder;
import com.digitalskies.demo.security.JWTService;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AuthService {

    private UserRepository userRepository;

    private HashEncoder hashEncoder;

    private JWTService jwtService;

    private RefreshTokenRepository refreshTokenRepository;

    public AuthService(UserRepository userRepository, HashEncoder hashEncoder, JWTService jwtService, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.hashEncoder = hashEncoder;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    TokenPair login(String email, String password) throws Exception{

        var userOptional=userRepository.findByEmail(email).stream().findFirst();

        if(userOptional.isEmpty()){

            throw new IllegalArgumentException("Invalid credentials");
        }

        var user= userOptional.get();

        if(!hashEncoder.compareHash(password,user.getHashedPassword())){
            throw new IllegalArgumentException("Invalid credentials");
        }

        String accessToken = jwtService.generateAccessToken(email);

        String refreshToken = jwtService.generateRefreshToken(email);

        storeRefreshToken(email,refreshToken);

        return new TokenPair(accessToken,refreshToken);

    }

    void register(String email, String password){

        var userOptional=userRepository.findByEmail(email).stream().findFirst();

        if(userOptional.isPresent()){

            throw new IllegalArgumentException("Email already registered");
        }

        var hashedPassword = hashEncoder.encode(password);
        var user = new User(email,hashedPassword);

        userRepository.save(user);
    }

    @Transactional
    String refreshToken(String rawRefreshToken){

        if(!jwtService.validateRefreshToken(rawRefreshToken)){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        var userId= jwtService.getUsernameFromToken(rawRefreshToken);

        var optionalRefreshToken = refreshTokenRepository.findByUserId(userId).stream().findFirst();

        if(optionalRefreshToken.isEmpty()){
            throw new IllegalArgumentException("Invalid refresh token");
        }

        return jwtService.generateAccessToken(userId);


    }

    void storeRefreshToken(String userId,String rawToken) throws Exception {

        String hashedToken = hashToken(rawToken);

        long now = System.currentTimeMillis();

        RefreshToken refreshToken= new RefreshToken(
              userId,
              hashedToken,
              now,
              now +jwtService.REFRESH_TOKEN_VALIDITY
        );

        refreshTokenRepository.save(refreshToken);

    }

    String hashToken(String rawToken) throws Exception {

        try {
            var hashedToken = MessageDigest.getInstance("SHA-256").digest(rawToken.getBytes());

            return Base64.getEncoder().encodeToString(hashedToken);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        }
    }


}
