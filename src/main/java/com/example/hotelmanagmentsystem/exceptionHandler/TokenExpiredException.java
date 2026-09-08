package com.example.hotelmanagmentsystem.exceptionHandler;

public class TokenExpiredException extends RuntimeException
{
    public TokenExpiredException(String message) {
        super(message);
    }
}
