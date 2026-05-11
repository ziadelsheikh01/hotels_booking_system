package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.dto.room.RoomResponse;
import com.example.hotelmanagmentsystem.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController
{
    private RoomService roomService ;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @GetMapping("/room/{id}/availability")
    public ResponseEntity<Boolean> checkAvailability(@PathVariable Long id)
    {
        return ResponseEntity.ok(roomService.checkAvailability(id));
    }
    @GetMapping("/hotel/{hotelId}/room")
    public ResponseEntity<List<RoomResponse>> findByHotelId(@PathVariable long hotelId)
    {
        return  ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }

    @PostMapping("/hotel/{hotelId}/room")
    public ResponseEntity addRoomToHotel(@PathVariable long hotelId,@RequestBody RoomRequest roomRequest)
    {
        roomService.AddRoom(hotelId,roomRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
