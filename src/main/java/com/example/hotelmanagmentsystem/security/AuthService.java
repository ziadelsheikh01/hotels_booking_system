package com.example.hotelmanagmentsystem.security;

import com.example.hotelmanagmentsystem.dto.user.*;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.enums.Role;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.mapper.UserMapper;
import com.example.hotelmanagmentsystem.repository.RefreshTokenRepository;
import com.example.hotelmanagmentsystem.repository.UserRepository;
import com.example.hotelmanagmentsystem.service.RefreshTokenService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
    private final UserMapper userMapper ;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager ;
   private  final JwtService jwtService ;
   private final RefreshTokenService refreshTokenService;


    public AuthService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Transactional
    public UserResponse register(AddUserRequest addUserRequest)
    {
        addUserRequest.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        User user = userMapper.toEntity(addUserRequest);
        user.setActive(true);
        user.setRole(Role.USER);
        try
        {
            return userMapper.toDto(userRepository.save(user));
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw new AlreadyExistException("email is already exist");
        }
    }

    @Transactional
    public TokenResponse login(LoginRequest loginRequest)
    {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
       CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();
        User user=customUserDetails.getUser();
        String accessToken = jwtService.generateToken(loginRequest.getEmail());
        refreshTokenService.deleteToken(user);
        String refreshToken = refreshTokenService.createToken(user).getToken();
        return new TokenResponse(accessToken,refreshToken);
    }


    @Transactional
    public void logout()
    {
        //get current user
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        //delete token by user
        refreshTokenService.deleteToken(user);
    }


}
