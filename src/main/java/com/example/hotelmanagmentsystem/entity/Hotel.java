package com.example.hotelmanagmentsystem.entity;

import com.example.hotelmanagmentsystem.enums.HotelStars;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @Column(name = "name" ,unique = true ,nullable = false)
    private String name ;

    @Column(name = "address" ,nullable = false)
    private  String address ;

    @Column(name = "phone_number" , unique = true , nullable = false)
    private  String phoneNumber ;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private HotelStars rating ;

    @OneToMany(mappedBy = "hotel" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    @JsonIgnore
    private List<Room> rooms ;
    public Hotel() {
    }

    public Hotel(String name, String address, String phoneNumber, HotelStars rating) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public HotelStars getRating() {
        return rating;
    }

    public void setRating(HotelStars rating) {
        this.rating = rating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public  void addRoom (Room room)
    {
        rooms.add(room) ;
        room.setHotel(this);
    }
    public void  deleteRoom(Room room)
    {
        rooms.remove(room);
        room.setHotel(null);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rating=" + rating +
                '}';
    }
}
