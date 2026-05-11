package com.example.hotelmanagmentsystem.dto.hotel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateHotelRequest
{

    @Size(min = 6 , max = 70 , message = "the hotel name size should be between 6 and 70 letters")
    private String name ;
    @Size(min = 4 , max = 30 , message = "the hotel address size should be between 4 and 30 letters")
    private  String address ;

    @Size(min = 11 , max = 11 , message = "phone number should contain 11 digits")
    private  String phoneNumber ;

    public UpdateHotelRequest() {
    }

    public UpdateHotelRequest(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "UpdateHotelRequest{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
