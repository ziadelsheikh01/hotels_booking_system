package com.example.hotelmanagmentsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
public class RefreshToken
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private long id ;
    @Column(name = "token" , unique = true , nullable = false)
    private String token ;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false , unique = true)
    private User user ;

    @Column(name = "expiry_date" , nullable = false )
    private LocalDateTime expiryDate ;

    public RefreshToken() {
    }

    public RefreshToken(String token, User user, LocalDateTime expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public long getId() {
        return id;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }


}
