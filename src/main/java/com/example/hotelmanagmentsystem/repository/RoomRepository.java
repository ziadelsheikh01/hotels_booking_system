package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long>
{

    @Query("""
            select count(r) > 0 
            from Room r 
            where r.id = :id
            and r.roomStatus <> 'MAINTENANCE'
            """)
    public boolean checkAvailability (@Param("id") Long id) ;


    public List<Room> findByHotelId (Long hotelId);

    @Query("""
            select count(r) > 0
            from Room r
            where r.hotel.id = :hotelId
            and  r.number =:roomNumber
            """)
    public boolean findRoomInHotel (@Param("hotelId") long hotelId,@Param("roomNumber") long roomNumber);

}
