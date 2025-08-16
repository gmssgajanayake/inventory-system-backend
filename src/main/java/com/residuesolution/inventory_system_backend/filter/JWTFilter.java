package com.residuesolution.inventory_system_backend.filter;

import com.residuesolution.inventory_system_backend.dto.response.user.UserResponseDTO;
import com.residuesolution.inventory_system_backend.service.JWTService;
import com.residuesolution.inventory_system_backend.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    public JWTFilter(JWTService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
          @NotNull  HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");


         /*
        Check if the Authorization header is not null and starts with "Bearer "
        If it is not, continue with spring's filter chain
        */
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }


        String jwtToken = authorization.substring(7);
        String username = jwtService.getUsername(jwtToken);

        // If the username is null, it means the JWT token is invalid or expired
        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserResponseDTO validUsername = userService.findUserByUsername(username);

        if (validUsername == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // If already authenticated, continue with the filter chain
        if (SecurityContextHolder.getContext().getAuthentication() != null){
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails =
                User.builder()
                        .username(validUsername.getUsername())
                        .password(validUsername.getPassword())
                        .roles(validUsername.getRole().toString())
                        .build();

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

        token.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(token);

        System.out.println(token);

        filterChain.doFilter(request, response);

    }

}
