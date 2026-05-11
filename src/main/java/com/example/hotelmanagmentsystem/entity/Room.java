package com.example.hotelmanagmentsystem.entity;

import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "room"
,uniqueConstraints = @UniqueConstraint(columnNames = {"number" , "hotel_id"}))
public class Room
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @Column(name = "number" , nullable = false)
    private Integer number ;

    @Column(name = "price" ,nullable = false)
    private BigDecimal price ;
    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private RoomStatus roomStatus ;
    @Enumerated(EnumType.STRING )
    @Column(name = "type" , nullable = false)
    private RoomType roomType ;

    @ManyToOne( fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name = "hotel_id" ,nullable = false)
    @JsonIgnore
    private  Hotel hotel ;

    public Room() {
    }

    public Room(Integer number, BigDecimal price, RoomStatus roomStatus, RoomType roomType, Hotel hotel) {
        this.number = number;
        this.price = price;
        this.roomStatus = roomStatus;
        this.roomType = roomType;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", price=" + price +
                ", roomStatus=" + roomStatus +
                ", roomType=" + roomType +
                '}';
    }
}
