package com.example.hotelmanagmentsystem.dto.room;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class RoomRequest {
    @NotNull(message = "number field is required")
    @Positive(message = "room number must be positive")
    private Integer number ;


    @NotNull(message = "price field is required")
    @Positive(message = "price must be positive number")
    private BigDecimal price ;
    @NotNull(message = "room roomStatus is required")
    private RoomStatus roomStatus ;
    @NotNull(message = "room roomType is required")
    private RoomType roomType ;

    public RoomRequest(Integer number, BigDecimal price, RoomStatus roomStatus, RoomType roomType) {
        this.number = number;
        this.price = price;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "RoomRequest{" +
                "number=" + number +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", roomType=" + roomType +
                '}';
    }
}
