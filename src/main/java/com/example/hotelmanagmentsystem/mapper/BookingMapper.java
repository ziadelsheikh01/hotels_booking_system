package com.example.hotelmanagmentsystem.mapper;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;
import com.example.hotelmanagmentsystem.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,
uses = RoomMapper.class)
public interface BookingMapper
{
    public Booking toEntity (BookingRequest bookingRequest) ;

    @Mapping(target = "userId" , source = "user.id")
    public BookingResponse toDto (Booking booking);
}
