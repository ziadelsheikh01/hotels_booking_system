package com.example.hotelmanagmentsystem.mapper;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper
{
    public Booking toEntity (BookingRequest bookingRequest) ;
}
