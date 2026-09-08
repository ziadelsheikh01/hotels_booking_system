package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.user.RefreshTokenRequest;
import com.example.hotelmanagmentsystem.dto.user.TokenResponse;
import com.example.hotelmanagmentsystem.entity.RefreshToken;
import com.example.hotelmanagmentsystem.entity.User;

public interface RefreshTokenService {
    public String generate ();
    public void deleteToken(User user);
    public RefreshToken createToken (User user) ;

    public TokenResponse refresh (RefreshTokenRequest refreshTokenRequest);
}
