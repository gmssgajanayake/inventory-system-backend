package com.residuesolution.inventory_system_backend.config.security;

import com.residuesolution.inventory_system_backend.filter.JWTFilter;
import com.residuesolution.inventory_system_backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

import static com.residuesolution.inventory_system_backend.config.permission.Role.ADMIN;
import static com.residuesolution.inventory_system_backend.config.permission.Role.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final JWTFilter jwtFilter;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, JWTFilter jwtFilter) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http ) throws Exception {

       return http
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                        authorizeHttpRequestsCustomizer
                                .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST,"api/auth/register").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"api/users").hasAnyRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"api/users/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"api/users/**").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.GET,"/api/items").permitAll()
                                .requestMatchers(HttpMethod.POST,"api/items").hasRole(ADMIN.name())
                                .requestMatchers(HttpMethod.PUT,"api/items/**").hasAnyRole(ADMIN.name(), USER.name())
                                .requestMatchers(HttpMethod.DELETE,"api/items/**").hasRole(ADMIN.name())
                                .anyRequest().authenticated()
                )
               .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
               .authenticationProvider(daoAuthenticationProvider())
               //.httpBasic(Customizer.withDefaults()) // Enable basic authentication for all requests
               .build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(userService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

}
