package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.user.RefreshTokenRequest;
import com.example.hotelmanagmentsystem.dto.user.TokenResponse;
import com.example.hotelmanagmentsystem.entity.RefreshToken;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.exceptionHandler.TokenExpiredException;
import com.example.hotelmanagmentsystem.repository.RefreshTokenRepository;
import com.example.hotelmanagmentsystem.security.CustomUserDetails;
import com.example.hotelmanagmentsystem.security.JwtService;
import com.example.hotelmanagmentsystem.service.RefreshTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
@Service
public class RefreshTokenServiceImp implements RefreshTokenService
{
    private final RefreshTokenRepository refreshTokenRepository;
    private  final JwtService jwtService;

    @Value("${Jwt.refresh-token.expiration}")
    private long RefreshTokenExpiration;

    public RefreshTokenServiceImp(RefreshTokenRepository refreshTokenRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }

    @Override
    public String generate() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    @Override
    @Transactional
    public void deleteToken(User user) {
        refreshTokenRepository.deleteByUser(user);

    }
    @Transactional
    @Override
    public RefreshToken createToken(User user)
    {
        //delete token by user
        refreshTokenRepository.deleteByUser(user);
        //generate token
        String token = generate();
        //calc expiration date
        LocalDateTime expiry_date = LocalDateTime.now().plusSeconds(RefreshTokenExpiration/1000);

        // create refresh token
        RefreshToken refreshToken = new RefreshToken(token,user,expiry_date);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    @Transactional
    public TokenResponse refresh(RefreshTokenRequest refreshTokenRequest) {

        //find refresh token
       RefreshToken refreshToken= refreshTokenRepository.findByToken(refreshTokenRequest.getRefreshToken())
               .orElseThrow(()->new NotFoundException("refresh token not found"));
        // check refresh token expiration
            if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now()))
            {
                throw  new TokenExpiredException("Refresh token is expired");
            }
           User user = refreshToken.getUser();
        //delete existed refresh token
        refreshTokenRepository.delete(refreshToken);
        //create new refresh token
        RefreshToken newRefreshToken = this.createToken(user);
        //create new jwt access token
        String accessToken = jwtService.generateToken(user.getEmail());
        //return token response the contain the new access token and new refresh token
        return new TokenResponse(accessToken,newRefreshToken.getToken());
    }


}
