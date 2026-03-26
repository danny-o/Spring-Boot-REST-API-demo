package com.digitalskies.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HashEncoder {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String encode(String string){
        return bCryptPasswordEncoder.encode(string);
    }

    public Boolean compareHash(String rawString, String hashedString){
        return bCryptPasswordEncoder.matches(rawString,hashedString);
    }
}
