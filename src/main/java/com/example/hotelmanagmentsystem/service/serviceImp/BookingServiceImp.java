package com.example.hotelmanagmentsystem.service.serviceImp;

import com.example.hotelmanagmentsystem.dto.booking.BookingRequest;
import com.example.hotelmanagmentsystem.dto.booking.BookingResponse;
import com.example.hotelmanagmentsystem.entity.Booking;
import com.example.hotelmanagmentsystem.entity.Room;
import com.example.hotelmanagmentsystem.entity.User;
import com.example.hotelmanagmentsystem.enums.BookingStatus;
import com.example.hotelmanagmentsystem.enums.Role;
import com.example.hotelmanagmentsystem.enums.RoomStatus;
import com.example.hotelmanagmentsystem.exceptionHandler.BadRequestException;
import com.example.hotelmanagmentsystem.exceptionHandler.BusinessException;
import com.example.hotelmanagmentsystem.exceptionHandler.NotFoundException;
import com.example.hotelmanagmentsystem.mapper.BookingMapper;
import com.example.hotelmanagmentsystem.repository.BookingRepository;
import com.example.hotelmanagmentsystem.repository.RoomRepository;
import com.example.hotelmanagmentsystem.repository.UserRepository;
import com.example.hotelmanagmentsystem.security.CustomUserDetails;
import com.example.hotelmanagmentsystem.service.BookingService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImp(RoomRepository roomRepository, UserRepository userRepository,
                             BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        if (!bookingRequest.getCheckIn().isBefore(bookingRequest.getCheckOut())) {
            throw new BadRequestException("Check-in must be before check-out");
        }

        Room room = roomRepository.findByIdForUpdate(bookingRequest.getRoomId())
                .orElseThrow(() -> new NotFoundException("room not found"));

        if (room.getRoomStatus() != RoomStatus.AVAILABLE) {
            throw new BusinessException("the room is not available now");
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();

        if (bookingRepository.checkBookingOverlapping(
                bookingRequest.getRoomId(), bookingRequest.getCheckIn(), bookingRequest.getCheckOut(),
                BookingStatus.CONFIRMED)) {
            throw new BusinessException("there is a booking at this interval");
        }

        long nights = bookingRequest.getCheckOut().toEpochDay() - bookingRequest.getCheckIn().toEpochDay();
        Booking booking = bookingMapper.toEntity(bookingRequest);
        booking.setPrice(room.getPrice().multiply(BigDecimal.valueOf(nights)));
        booking.setRoom(room);
        booking.setUser(user);
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public List<BookingResponse> findByUser() {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = customUserDetails.getUser();
        return bookingMapper.toDto(bookingRepository.findByUserId(user.getId()));
    }

    @Override
    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("booking not found"));

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User currentUser = customUserDetails.getUser();

        boolean isOwner = booking.getUser().getId().equals(currentUser.getId());
        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        if (!isOwner && !isAdmin) {
            throw new AccessDeniedException("you are not allowed to cancel this booking");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BusinessException("booking is already cancelled");
        }

        if (!LocalDate.now().isBefore(booking.getCheckIn())) {
            throw new BusinessException("booking can no longer be cancelled on or after check-in date");
        }

        booking.setStatus(BookingStatus.CANCELLED);
    }
}
