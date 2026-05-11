package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.user.AddUserRequest;
import com.example.hotelmanagmentsystem.dto.user.LoginRequest;
import com.example.hotelmanagmentsystem.dto.user.UserResponse;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.enums.Role;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.UserMapper;
import com.example.hotelmanagmentsystem.repository.UserRepository;
import com.example.hotelmanagmentsystem.security.CustomUserDetails;
import com.example.hotelmanagmentsystem.service.UserService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImp implements UserService {



}
