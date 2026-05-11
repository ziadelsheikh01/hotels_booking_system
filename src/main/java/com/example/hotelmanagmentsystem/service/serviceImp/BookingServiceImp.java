package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.entity.Booking;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.service.BookingService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public class BookingServiceImp implements BookingService {
    @Override
    @Transactional
    public Booking create(BookingRequest bookingRequest)
    {

        return  null ;

    }
}
