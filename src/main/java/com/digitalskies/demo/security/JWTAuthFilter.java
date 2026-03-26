package com.digitalskies.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    private Logger logger= LoggerFactory.getLogger(getClass());

    public JWTAuthFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){

            if(jwtService.validateAccessToken(authHeader)){
                var userId= jwtService.getUsernameFromToken(authHeader);

                logger.debug("found user id {}",userId);

                var auth = new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());

                SecurityContextHolder.getContext().setAuthentication(auth);

            }
        }


        filterChain.doFilter(request,response);

    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        String path = request.getServletPath();
//        return path.equals("/auth/login") ||
//                path.equals("/auth/register");
//    }
}
