package com.example.hotelmanagmentsystem.mapper;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.UpdateHotelRequest;
import com.example.hotelmanagmentsystem.entity.Hotel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelMapper {


    public HotelResponse toDto (Hotel hotel) ;

    public Hotel toEntity (AddHotelRequest addHotelRequest) ;
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  void update (UpdateHotelRequest updateHotelRequest , @MappingTarget Hotel hotel) ;

    public List<HotelResponse> toDto (List<Hotel> hotels) ;
}
