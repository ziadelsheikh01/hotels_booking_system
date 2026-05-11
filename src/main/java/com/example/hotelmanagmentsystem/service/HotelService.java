package com.example.hotelmanagmentsystem.service;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.HotelSearchRequest;
import com.example.hotelmanagmentsystem.dto.hotel.UpdateHotelRequest;
import com.example.hotelmanagmentsystem.entity.Hotel;

import java.util.List;

public interface HotelService
{
    public List<HotelResponse> search (HotelSearchRequest hotelSearchRequest) ;
    public List<HotelResponse> findAll () ;
    public HotelResponse findById (Long id);

    public  HotelResponse create (AddHotelRequest addHotelRequest) ;

    public  void update (Long id , UpdateHotelRequest updateHotelRequest) ;


}
