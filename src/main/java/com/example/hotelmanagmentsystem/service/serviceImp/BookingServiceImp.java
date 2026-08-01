package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;
import com.example.hotelmanagmentsystem.entity.Booking;
import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.exceptionHandler.AlreadyExistException;
import com.example.hotelmanagmentsystem.exceptionHandler.BadRequestException;
import com.example.hotelmanagmentsystem.exceptionHandler.BusinessException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.BookingMapper;
import com.example.hotelmanagmentsystem.repository.BookingRepository;
import com.example.hotelmanagmentsystem.repository.RoomRepository;
import com.example.hotelmanagmentsystem.repository.UserRepository;
import com.example.hotelmanagmentsystem.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BookingServiceImp implements BookingService {

    private final RoomRepository roomRepository ;
    private  final UserRepository userRepository;
    private  final BookingRepository bookingRepository ;
    private  final BookingMapper bookingMapper ;

    public BookingServiceImp(RoomRepository roomRepository, UserRepository userRepository, BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest)
    {
        if (!bookingRequest.getCheckIn()
                .isBefore(bookingRequest.getCheckOut())) {
            throw  new BadRequestException("Check-in must be before check-out");

        }

        // check if the room exist
        Room room =roomRepository.findByIdForUpdate(bookingRequest.getRoomId()).orElseThrow(()->new NotFoundException("room not found"));

        //check if room is available
        if (room.getRoomStatus()!=RoomStatus.AVAILABLE)
        {
            throw  new BusinessException("the room is not available now");
        }
        //check if the user exist
        User user = userRepository.findById(bookingRequest.getUserId()).orElseThrow(()->new NotFoundException("user not found"));

        // check if the current booking is overlapping with another booking
        if (bookingRepository.checkBookingOverlapping(bookingRequest.getRoomId(),bookingRequest.getCheckIn(),bookingRequest.getCheckOut()))
        {
            throw new BusinessException("there is a booking at this interval");
        }
        long nights = bookingRequest.getCheckOut()
                .toEpochDay()
                - bookingRequest.getCheckIn().toEpochDay();
        //create booking
        Booking booking = bookingMapper.toEntity(bookingRequest) ;
        booking.setPrice(room.getPrice().multiply(BigDecimal.valueOf(nights)));
        booking.setRoom(room);
        booking.setUser(user);
       return bookingMapper.toDto(bookingRepository.save(booking));

    }
}
