package com.example.corzello.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    AuthenticationProvider authenticationProvider ;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
                        req -> req.requestMatchers("/userapi/register","/Module/**","api/vote/**","/Program/**","/userapi/authenticate","/publications/**","/recruitment/**","/Quiz/**","/comments/**","/scrape/**","/userapi/forgot-password","/userapi/googleSignIn","/userapi/set-password").permitAll()
                                .requestMatchers("/userapi/role/addtoUser","/userapi/allusers").hasAnyAuthority("ROLE_Admin")
                                .anyRequest()
                                .authenticated()
                ).sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exception)-> exception.accessDeniedPage("/userapi/accessDenied"));
        return http.build();
    }
}
