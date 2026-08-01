package com.example.hotelmanagmentsystem.dto.booking;

import com.example.hotelmanagmentsystem.dto.room.RoomResponse;
import com.example.hotelmanagmentsystem.entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponse {

    private  Long id ;
    private BigDecimal price ;
    private LocalDate checkIn ;
    private LocalDate checkOut ;
    private LocalDateTime createdAt ;
    private RoomResponse room;

    private  Long userId ;

    public BookingResponse(Long id, BigDecimal price, LocalDate checkIn, LocalDate checkOut, LocalDateTime createdAt, RoomResponse roomResponse, Long userId) {
        this.id = id;
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.createdAt = createdAt;
        this.room = roomResponse;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public RoomResponse getRoom() {
        return room;
    }

    public void setRoom(RoomResponse roomResponse) {
        this.room = roomResponse;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "id=" + id +
                ", price=" + price +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", createdAt=" + createdAt +
                ", roomResponse=" + room +
                ", userId=" + userId +
                '}';
    }
}
