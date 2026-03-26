package com.digitalskies.demo.login;

import org.springframework.stereotype.Service;

@Service
public class Authenticator {

    public boolean isValidCredentials(String name, String password){
        return name.equalsIgnoreCase("Dan")
                && password.equalsIgnoreCase("Daniel");


        // security
        // JWT - access & refresh tokens
        // encoder & decoder of password
        // store refresh tokens
        // login-> query user id -> compare passwords -> return refresh && access tokens
    }
}
