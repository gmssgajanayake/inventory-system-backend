package com.residuesolution.inventory_system_backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig{

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable());
//        http.authorizeHttpRequests(customizer -> customizer
//                .requestMatchers("/api/auth/**").permitAll()
//                .requestMatchers("/api/users/**").hasRole("ADMIN")
//                .anyRequest().authenticated());
        return http.build();
    }

}
