package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    List<BookingResponse> findByUser();
    void cancelBooking(Long bookingId);
}
