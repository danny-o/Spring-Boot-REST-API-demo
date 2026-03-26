package com.digitalskies.demo.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfiguration {

    private JWTAuthFilter jwtAuthFilter;

    public SpringSecurityConfiguration(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity){

        // Requires every incoming HTTP request to be authenticated
//        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().authenticated());


        // Enables Spring's default login form
//        httpSecurity.formLogin(Customizer.withDefaults());



        // Disables all security-related HTTP response headers
//        httpSecurity.headers(AbstractHttpConfigurer::disable);

        // Turns off CSRF (Cross-Site Request Forgery) protection. Common for REST APIs or during development
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        httpSecurity.sessionManagement(policy->policy.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(
              auth->
                        auth.requestMatchers("/auth/**")
                      .permitAll()
                       .dispatcherTypeMatchers(
                              DispatcherType.ERROR,
                              DispatcherType.FORWARD
                      )
                      .permitAll()
                      .anyRequest()
                      .authenticated())
                .exceptionHandling(
                        configurer->
                         configurer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();


    }



}
