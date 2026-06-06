package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long>
{

    @Query("""
            select count(r) > 0 
            from Room r 
            where r.id = :id
            and r.roomStatus <> 'MAINTENANCE'
            """)
    public boolean checkAvailability (@Param("id") Long id) ;

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
           SELECT r
           FROM Room r
           WHERE r.id = :roomId
           """)
    Optional<Room> findByIdForUpdate(@Param("roomId") Long roomId);

    public List<Room> findByHotelId (Long hotelId);

    @Query("""
            select count(r) > 0
            from Room r
            where r.hotel.id = :hotelId
            and  r.number =:roomNumber
            """)
    public boolean findRoomInHotel (@Param("hotelId") long hotelId,@Param("roomNumber") long roomNumber);

}
