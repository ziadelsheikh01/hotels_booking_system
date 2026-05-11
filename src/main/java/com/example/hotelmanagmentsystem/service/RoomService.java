package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.dto.room.RoomResponse;
import com.example.hotelmanagmentsystem.entity.Room;

import java.util.List;

public interface RoomService
{
    public Boolean checkAvailability(long id) ;
    public List<RoomResponse> findByHotelId (long id) ;
    public void AddRoom (long hotelId, RoomRequest roomRequest);
}
