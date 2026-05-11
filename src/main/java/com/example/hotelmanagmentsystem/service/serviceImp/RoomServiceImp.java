package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.room.RoomRequest;
import com.example.hotelmanagmentsystem.dto.room.RoomResponse;
import com.example.hotelmanagmentsystem.entity.Hotel;
import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.RoomMapper;
import com.example.hotelmanagmentsystem.repository.HotelRepository;
import com.example.hotelmanagmentsystem.repository.RoomRepository;
import com.example.hotelmanagmentsystem.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImp implements RoomService
{
    private RoomRepository roomRepository ;
    private HotelRepository hotelRepository;
    private RoomMapper roomMapper ;

    public RoomServiceImp(RoomRepository roomRepository, HotelRepository hotelRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.roomMapper = roomMapper;
    }

    public Boolean checkAvailability (long id)
    {
        return roomRepository.checkAvailability(id) ;
    }



    @Override
    public List<RoomResponse> findByHotelId(long id) {
        return roomMapper.toDto(roomRepository.findByHotelId(id));
    }

    @Override
    @Transactional
    public void AddRoom(long hotelId, RoomRequest roomRequest) {
        // check if the hotel is already exist or not
      Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new NotFoundException("hotel is not found"));

      //check if room is already exist in hotel or not
        if (roomRepository.findRoomInHotel(hotelId,roomRequest.getNumber()))
        {
            throw new AlreadyExistException("room is already exist") ;
        }
     //add room
        Room room = roomMapper.toEntity(roomRequest);
        room.setHotel(hotel);
        roomRepository.save(room);
    }


}
