package com.example.hotelmanagmentsystem.dto.user;

import com.example.hotelmanagmentsystem.enums.Role;
import jakarta.validation.constraints.*;

public class AddUserRequest
{
    @NotBlank(message = "first name name is required")
    @Size(min = 2 , max = 30 , message = "firstName must be between 2 and 30 character")
    private String firstName;
    @NotBlank(message = "last name is required ")
    @Size(min = 2 , max = 30 , message = "last name must be between 2 and 30 character")
    private String lastName;
    @NotBlank(message = "email is required")
    @Email(message = "write valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{10,30}$",
            message = "Password must be 10-30 chars and include uppercase, lowercase, number, and special character"
    )
    private String password;


    public AddUserRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "AddUserRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
