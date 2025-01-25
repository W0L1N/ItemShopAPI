package com.kacwol.itemShop.security.service;

import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.domain.model.UserType;
import com.kacwol.itemShop.security.payload.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User map(SignupRequest request) {
        return new User(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                request.getUsername(),
                request.getEmail(),
                UserType.CUSTOMER
        );
    }
}
