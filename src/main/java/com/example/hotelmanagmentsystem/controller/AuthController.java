package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.user.AddUserRequest;
import com.example.hotelmanagmentsystem.dto.user.LoginRequest;
import com.example.hotelmanagmentsystem.dto.user.UserResponse;
import com.example.hotelmanagmentsystem.security.AuthService;
import com.example.hotelmanagmentsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
   private final AuthService authService ;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register( @RequestBody @Valid AddUserRequest addUserRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(addUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> register( @RequestBody @Valid LoginRequest loginRequest)
    {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }
}
