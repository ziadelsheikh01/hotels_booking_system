package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.RefreshToken;
import com.example.hotelmanagmentsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>
{
    public Optional<RefreshToken> findByToken(String token) ;

    public  void deleteByUser (User user) ;
}
