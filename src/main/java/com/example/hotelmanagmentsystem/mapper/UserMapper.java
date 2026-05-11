package com.example.hotelmanagmentsystem.mapper;

import com.example.hotelmanagmentsystem.dto.user.AddUserRequest;
import com.example.hotelmanagmentsystem.dto.user.UserResponse;
import com.example.hotelmanagmentsystem.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    public UserResponse toDto(User user);

    public User toEntity (AddUserRequest addUserRequest);
}
