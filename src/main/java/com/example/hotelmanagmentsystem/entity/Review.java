package com.example.hotelmanagmentsystem.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "review")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;
    @Column(name = "comment")
    private String comment ;
    @Column(name = "rating" , nullable = false)
    private Integer rating ;


    @ManyToOne(fetch = FetchType.LAZY ,optional = false)
    @JoinColumn(name = "user_id" ,nullable = false )
    private  User user ;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "hotel_id" ,nullable = false)
    private Hotel hotel;

    public Review() {
    }

    public Review(String comment, Integer rating, User user , Hotel hotel)
    {
        this.comment = comment;
        this.rating = rating;
        this.user = user ;
        this.hotel = hotel ;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}
