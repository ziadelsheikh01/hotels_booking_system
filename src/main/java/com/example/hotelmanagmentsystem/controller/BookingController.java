package com.example.hotelmanagmentsystem.controller;
import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;
import com.example.hotelmanagmentsystem.entity.Booking;
import com.example.hotelmanagmentsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/booking")
public class BookingController
{
    private  final BookingService bookingService ;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    public ResponseEntity<BookingResponse> createBooking (@Valid @RequestBody BookingRequest bookingRequest)
    {
        return  new ResponseEntity<>(bookingService.createBooking(bookingRequest) , HttpStatus.CREATED);
    }
}
