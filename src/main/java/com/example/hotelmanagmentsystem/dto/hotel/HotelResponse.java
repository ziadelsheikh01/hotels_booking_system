package com.example.hotelmanagmentsystem.dto.hotel;

import com.example.hotelmanagmentsystem.enums.HotelStars;
import jakarta.persistence.*;

public class HotelResponse
{
    private Long id ;
    private String name ;

    private  String address ;

    private  String phoneNumber ;
    private HotelStars rating ;

    public HotelResponse(Long id, String name, String address, String phoneNumber, HotelStars rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "HotelResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", rating=" + rating +
                '}';
    }
}
