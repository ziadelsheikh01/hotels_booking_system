package com.example.hotelmanagmentsystem.exceptionHandler;

public class BusinessException extends  RuntimeException
{
    public BusinessException (String message)
    {
        super(message);
    }
}
