package com.example.hotelmanagmentsystem.exceptionHandler;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message)
    {
        super(message);
    }
}
