package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;
import com.example.hotelmanagmentsystem.entity.Booking;

public interface BookingService {
    public BookingResponse createBooking (BookingRequest bookingRequest) ;
}
