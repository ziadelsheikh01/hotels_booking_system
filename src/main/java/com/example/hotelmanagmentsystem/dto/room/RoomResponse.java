package com.example.hotelmanagmentsystem.dto.room;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import java.math.BigDecimal;
public class RoomResponse {

    private Long id ;
    private Integer number ;

    private BigDecimal price ;

    private RoomStatus roomStatus ;

    private RoomType roomType ;

    public RoomResponse(Long id, Integer number, BigDecimal price, RoomStatus roomStatus, RoomType roomType) {
        this.id = id;
        this.number = number;
        this.price = price;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "RoomResponse{" +
                "id=" + id +
                ", number=" + number +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", roomType=" + roomType +
                '}';
    }
}
