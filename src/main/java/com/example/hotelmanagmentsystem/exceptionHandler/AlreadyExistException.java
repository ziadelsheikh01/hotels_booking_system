package com.example.hotelmanagmentsystem.exceptionHandler;

public class AlreadyExistException extends  RuntimeException
{
    public AlreadyExistException(String message) {
        super(message);
    }
}
