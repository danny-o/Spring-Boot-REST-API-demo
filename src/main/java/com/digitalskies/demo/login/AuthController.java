package com.digitalskies.demo.login;


import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private AuthService authService;

    private Logger logger=LoggerFactory.getLogger(getClass());


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    void register(@Valid @RequestBody AuthRequest authRequest){

        authService.register(authRequest.email(),authRequest.password());
    }

    @PostMapping(path = "/login")
    TokenPair login(@Valid @RequestBody AuthRequest authRequest) throws Exception {

            return authService.login(authRequest.email(),authRequest.password());

    }

    @PostMapping(path = "/refreshToken")
    AccessToken refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
            String refreshToken= authService.refreshToken(refreshTokenRequest.refreshToken());

            return new AccessToken(refreshToken);

    }
}
