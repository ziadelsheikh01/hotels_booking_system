package com.example.hotelmanagmentsystem.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddHotelRequest
{

    @Schema(
            description = "Hotel Name"  ,
            example = "Hilton Cairo"
    )
    @NotNull(message = "required field")
    @Size(min = 6 , max = 70 , message = "the hotel name size should be between 6 and 70 letters")
    private String name ;


    @Schema(description = "Hotel address" , example = "Nasr City, Cairo")
    @NotNull(message = "required field")
    @Size(min = 4 , max = 30 , message = "the hotel address size should be between 4 and 30 letters")
    private  String address ;


    @Schema(description = "Hotel phone number" ,example = "01111112555")
    @NotNull(message = "required field")
    @Size(min = 11 , max = 11 , message = "phone number should contain 11 digits")
    private  String phoneNumber ;

    public AddHotelRequest(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public AddHotelRequest() {
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
        return "AddHotelRequest{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
