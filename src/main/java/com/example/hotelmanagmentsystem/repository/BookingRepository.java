package com.example.hotelmanagmentsystem.repository;

import com.example.hotelmanagmentsystem.entity.Booking;
import com.example.hotelmanagmentsystem.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);

    @Query("""
            select count(b) > 0
            from Booking b
            where b.room.id = :roomId
            and b.status = :status
            and :checkIn < b.checkOut
            and :checkOut > b.checkIn
            """)
    boolean checkBookingOverlapping(
            @Param("roomId") Long roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("status") BookingStatus status);
}
