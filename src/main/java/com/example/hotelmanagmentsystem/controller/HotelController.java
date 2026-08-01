package com.example.hotelmanagmentsystem.controller;

import com.example.hotelmanagmentsystem.dto.error.ErrorResponse;
import com.example.hotelmanagmentsystem.dto.hotel.AddHotelRequest;
import com.example.hotelmanagmentsystem.dto.hotel.HotelResponse;
import com.example.hotelmanagmentsystem.dto.hotel.HotelSearchRequest;
import com.example.hotelmanagmentsystem.dto.hotel.UpdateHotelRequest;
import com.example.hotelmanagmentsystem.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Hotels",
        description = "Operations related to hotel management."
)
@RestController
@RequestMapping("api/hotel")
public class HotelController
{
    private HotelService hotelService ;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(
            summary = "search hotels"
            ,description = "return details of hotels based on the search criteria"
    )

    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200" , description = "hotels returned successfully")
            }
    )
    @PostMapping("/search")
    public ResponseEntity<List<HotelResponse>> search (@RequestBody HotelSearchRequest hotelSearchRequest)
    {
        return ResponseEntity.ok(hotelService.search(hotelSearchRequest)) ;
    }

    @Operation(
            summary = "get all hotels"
            ,description = "return details of all hotels"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200" , description = "hotels returned successfully")
    }
    )
    @GetMapping
    public ResponseEntity<List<HotelResponse>>  findAll ()
    {
        return ResponseEntity.ok(hotelService.findAll()) ;
    }

    @Operation(
            summary = "update hotel By id"
           , description = "partially update the hotel based on the update hotel request"
    )
    @ApiResponses({
     @ApiResponse(responseCode = "404" , description = "hotel with this id is not found" ,
     content = @Content(schema = @Schema(implementation = ErrorResponse.class))) ,
            @ApiResponse(responseCode = "409" , description = "the hotel name is already exist"
            ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))) ,
            @ApiResponse(responseCode ="200" , description = "hotel updated successfully") ,
            @ApiResponse(responseCode = "400" , description = "invalid request body")

    }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id , @RequestBody @Valid  UpdateHotelRequest updateHotelRequest)
    {
        hotelService.update(id , updateHotelRequest);
        return  ResponseEntity.ok("Hotel updated successfully") ;

    }

    @Operation(summary = "create hotel" , description = "add new hotels to db")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "409" , description = "hotel is already saved before"
                    ,content = @Content(schema = @Schema(implementation = ErrorResponse.class))) ,
                    @ApiResponse(responseCode = "201" , description = "hotel is added successfully") ,
                    @ApiResponse(responseCode = "400" , description = "invalid request body")
            }
    )
    @PostMapping
    public  ResponseEntity<HotelResponse> create (@RequestBody @Valid AddHotelRequest addHotelRequest)
    {
        HotelResponse hotelResponse =hotelService.create(addHotelRequest);
        return new ResponseEntity<>(hotelResponse, HttpStatus.CREATED) ;
    }
    @Operation(
            summary = "get hotel by id" ,
            description = "find the hotel using specific id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404" , description = "the hotel is not found" ,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))) ,
            @ApiResponse(responseCode = "200" , description = "the hotel is found and returned successfully")
    })
    @GetMapping("{id}")
    public ResponseEntity<HotelResponse> findById (@PathVariable Long id)
    {

       return ResponseEntity.ok(hotelService.findById(id));
    }

}
