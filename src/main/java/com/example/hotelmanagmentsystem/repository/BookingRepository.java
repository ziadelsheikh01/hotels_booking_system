package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long>
{
    public List<Booking> findByUserId (Long userId) ;

}
