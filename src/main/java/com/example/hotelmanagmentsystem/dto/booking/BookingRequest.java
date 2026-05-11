package com.example.hotelmanagmentsystem.dto.booking;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
public class BookingRequest
{
    @NotNull
    @FutureOrPresent(message = "checkIn date must be today or in next days")
    private LocalDate checkIn ;
    @NotNull
    @Future(message = "the checkOut date is invalid")
    private LocalDate checkOut ;
    @NotNull
    @Positive(message = "room id must be positive number")
    private Long roomId ;
    @NotNull
    @Positive(message = "userId must be positive number")
    private Long userId ;

    public BookingRequest() {
    }

    public BookingRequest(LocalDate checkIn, LocalDate checkOut, Long roomId, Long userId) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomId = roomId;
        this.userId = userId;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", roomId=" + roomId +
                ", userId=" + userId +
                '}';
    }
}
