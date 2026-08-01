package com.example.hotelmanagmentsystem.dto.hotel;

import com.example.hotelmanagmentsystem.enums.HotelStars;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

public class HotelResponse
{
    @Schema(description = "Hotel Id" , example = "2")
    private Long id ;
    @Schema(description = "Hotel name" , example = "Hilton Cairo")
    private String name ;

    @Schema(description = "Hotel Address" , example = "Nasr City, Cairo")
    private  String address ;
    @Schema(description = "Hotel phone number" ,example = "01111112555")
    private  String phoneNumber ;
    @Schema(description = "Hotel rating" , example = "ONE_STAR")
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
