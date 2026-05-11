package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.HotelSearchRequest;
import com.example.hotelmanagmentsystem.dto.hotel.UpdateHotelRequest;
import com.example.hotelmanagmentsystem.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotel")
public class HotelController
{
    private HotelService hotelService ;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<HotelResponse>> search (@RequestBody HotelSearchRequest hotelSearchRequest)
    {
        return ResponseEntity.ok(hotelService.search(hotelSearchRequest)) ;
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>>  findAll ()
    {
        return ResponseEntity.ok(hotelService.findAll()) ;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id , @RequestBody @Valid  UpdateHotelRequest updateHotelRequest)
    {
        hotelService.update(id , updateHotelRequest);
        return  ResponseEntity.ok("Hotel updated successfully") ;

    }

    @PostMapping
    public  ResponseEntity<HotelResponse> create (@RequestBody @Valid AddHotelRequest addHotelRequest)
    {
        HotelResponse hotelResponse =hotelService.create(addHotelRequest);
        return new ResponseEntity<>(hotelResponse, HttpStatus.CREATED) ;
    }

    @GetMapping("{id}")
    public ResponseEntity<HotelResponse> findById (@PathVariable Long id)
    {

       return ResponseEntity.ok(hotelService.findById(id));
    }

}
