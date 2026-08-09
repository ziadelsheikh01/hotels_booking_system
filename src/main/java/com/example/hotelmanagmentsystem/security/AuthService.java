package com.example.hotelmanagmentsystem.security;

import com.example.hotelmanagmentsystem.dto.user.AddUserRequest;
import com.example.hotelmanagmentsystem.dto.user.LoginRequest;
import com.example.hotelmanagmentsystem.dto.user.UserResponse;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.enums.Role;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.UserMapper;
import com.example.hotelmanagmentsystem.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    public AuthService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserResponse register(AddUserRequest addUserRequest)
    {
        addUserRequest.setPassword(passwordEncoder.encode(addUserRequest.getPassword()));
        User user = userMapper.toEntity(addUserRequest);
        user.setActive(true);
        //user.setRole(Role.User);
        try
        {
            return userMapper.toDto(userRepository.save(user));
        }
        catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            throw new AlreadyExistException("email is already exist");
        }
    }

    public String login(LoginRequest loginRequest)
    {
        Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        return jwtService.generateToken(loginRequest.getEmail());
    }
}
