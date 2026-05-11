package com.example.hotelmanagmentsystem.entity;

import com.example.hotelmanagmentsystem.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id ;

    @Column(name = "first_name" ,nullable = false)
    private  String firstName ;

    @Column(name = "last_name",nullable = false)
    private  String lastName ;

    @Column(name = "email" ,unique = true , nullable = false)
    private String email ;

    @Column(name = "password" , nullable = false)
    private String password ;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" ,nullable = false)
    private Role role ;

    @Column(name = "active")
    private boolean active =true;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Role role, boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }
}
