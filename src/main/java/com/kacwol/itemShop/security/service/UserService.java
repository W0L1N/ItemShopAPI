package com.kacwol.itemShop.security.service;


import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.domain.model.exception.EmailAlreadyInUseException;
import com.kacwol.itemShop.domain.model.exception.TokenRefreshException;
import com.kacwol.itemShop.infrastructure.UserRepo;
import com.kacwol.itemShop.security.model.CustomUserDetails;
import com.kacwol.itemShop.security.model.RefreshToken;
import com.kacwol.itemShop.security.payload.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;

    private final  RefreshTokenService refreshTokenService;

    private final JWTUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public String login(String user){
        return jwtUtils.generateToken(user);
    }

    public String refreshToken(String refreshToken){
        return refreshTokenService.findByToken(refreshToken)
                .map(e-> refreshTokenService.verifyExpiration(e))
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateToken(user.getEmail());
                    return token;
                })
                .orElseThrow(()-> new TokenRefreshException("Refresh token not found in database"));
    }

    public String createRefreshToken(Long userId){
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userId);
        return refreshToken.getToken();
    }

    public boolean register(SignupRequest signupRequest){
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new EmailAlreadyInUseException(signupRequest.getEmail());
        }
        User user = userMapper.map(signupRequest);
        userRepository.save(user);
        return true;
    }

    public User getAuthenticatedUser() {
        CustomUserDetails details = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findById(details.getId()).orElseThrow();
    }
}
