package com.example.hotelmanagmentsystem.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "booking"
, uniqueConstraints = @UniqueConstraint(columnNames = {"room_id" , "check_in" , "check_out"})
)
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id ;
    @Column(name = "price" , nullable = false)
    private BigDecimal price ;
    @Column(name = "check_in" ,nullable = false)
    private LocalDate checkIn ;
    @Column(name = "check_out" ,nullable = false)
    private LocalDate checkOut ;
    @Column(name = "created_at" ,nullable = false)
    private LocalDateTime createdAt ;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "room_id" ,nullable = false)
    private Room room ;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user ;

    public Booking() {
    }

    public Booking( BigDecimal price, LocalDate checkIn, LocalDate checkOut, Room room, User user) {
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.user = user;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", price=" + price +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", createdAt=" + createdAt +
                '}';
    }
}
