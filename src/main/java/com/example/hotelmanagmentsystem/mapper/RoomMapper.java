package com.example.hotelmanagmentsystem.mapper;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.dto.room.RoomResponse;
import com.example.hotelmanagmentsystem.entity.Room;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper
{
    public List<RoomResponse> toDto(List<Room> rooms);
    public Room toEntity (RoomRequest roomRequest);
    public RoomResponse toDto (Room room) ;
}
