package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.RoomMapper;
import com.example.hotelmanagmentsystem.repository.HotelRepository;
import com.example.hotelmanagmentsystem.repository.RoomRepository;
import com.example.hotelmanagmentsystem.service.serviceImp.RoomServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.NotActiveException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest
{
    @Mock
    private RoomRepository roomRepository ;
    @Mock
    private HotelRepository hotelRepository ;

    @Mock
    private RoomMapper roomMapper ;

    @InjectMocks
    private RoomServiceImp roomService ;

    private Hotel hotel ;
    private Room room;
    private  RoomRequest roomRequest;

    @BeforeEach
    public void setUp()
    {
        hotel = new Hotel("hilton" ,"giza" , "0111111111" , HotelStars.FIVE_STAR) ;
        room = new Room(1,BigDecimal.valueOf(2000),RoomStatus.AVAILABLE,RoomType.DOUBLE,hotel);
        roomRequest = new RoomRequest(2,BigDecimal.valueOf(2000),RoomStatus.AVAILABLE,RoomType.DOUBLE);
    }
    @Test
    public void shouldThrowException_WhenAddRoomToUnExistedHotel()
    {
        //arrange
        when(hotelRepository.findById(2L)).thenReturn(Optional.empty());
        //act and assert
        Assertions.assertThrows(NotFoundException.class,()->roomService.AddRoom(2,roomRequest));
    }

    @Test
    public void shouldSaveRoom_WhenAddValidRoomData()
    {
        //arrange
        when(hotelRepository.findById(any())).thenReturn(Optional.of(hotel));
        when(roomMapper.toEntity(any())).thenReturn(room);
        when(roomRepository.findRoomInHotel(
                1,
                roomRequest.getNumber()))
                .thenReturn(false);
        //act
        roomService.AddRoom(1,roomRequest);
        //assert
        verify(roomRepository).save(any());
    }

    //test case to test the behaviour when trying to add an existing room ins specific hotel

    @Test
    public void ShouldThrowException_WhenAddAlreadyExistRoomInHotel()
    {
        when(hotelRepository.findById(any())).thenReturn(Optional.of(hotel));
        when(roomRepository.findRoomInHotel(anyLong(),anyLong())).thenReturn(true);

        Assertions.assertThrows(AlreadyExistException.class,()->roomService.AddRoom(1,roomRequest));
        verify(roomRepository, never()).save(any());
    }
}
