package com.kacwol.itemShop.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.security.model.CustomUserDetails;
import com.kacwol.itemShop.security.service.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

import static com.kacwol.itemShop.security.config.JWTConstants.HEADER_STRING;
import static com.kacwol.itemShop.security.config.JWTConstants.TOKEN_PREFIX;


@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtils jwtUtils;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            User creds = new ObjectMapper()

                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()));

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String user = ((CustomUserDetails) authResult.getPrincipal()).getEmail();
        String token = jwtUtils.generateToken(user);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }
}

