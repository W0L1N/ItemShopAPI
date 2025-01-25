package com.kacwol.itemShop.security.service;


import com.kacwol.itemShop.domain.model.User;
import com.kacwol.itemShop.domain.model.exception.TokenRefreshException;
import com.kacwol.itemShop.infrastructure.UserRepo;
import com.kacwol.itemShop.security.model.RefreshToken;
import com.kacwol.itemShop.security.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.kacwol.itemShop.security.config.JWTConstants.REFRESH_EXPIRATION_TIME;

@Service
@Transactional
public class RefreshTokenService {

    private RefreshTokenRepository refreshTokenRepository;

    private UserRepo userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepo userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).get();
        Instant expiryDate = Instant.now().plusMillis(REFRESH_EXPIRATION_TIME);
        String token = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken(user, token, expiryDate);
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token was expired");
        }
        return token;
    }


}
