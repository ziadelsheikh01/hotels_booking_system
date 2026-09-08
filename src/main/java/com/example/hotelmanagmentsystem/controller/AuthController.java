package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.user.*;
import com.example.hotelmanagmentsystem.security.AuthService;
import com.example.hotelmanagmentsystem.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
   private final AuthService authService ;
   private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register( @RequestBody @Valid AddUserRequest addUserRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(addUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> register(@RequestBody @Valid LoginRequest loginRequest)
    {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest)
    {
        return ResponseEntity.status(HttpStatus.OK).body(refreshTokenService.refresh(refreshTokenRequest));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout ()
    {
        authService.logout();
        return ResponseEntity.noContent().build() ;
    }

}
