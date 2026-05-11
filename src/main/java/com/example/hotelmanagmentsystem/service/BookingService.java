package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.entity.Booking;

public interface BookingService {
    public Booking create (BookingRequest bookingRequest) ;
}
