package com.example.hotelmanagmentsystem.exceptionHandler;

public class NotFoundException extends  RuntimeException
{
    public NotFoundException(String message) {
        super(message);
    }
}
