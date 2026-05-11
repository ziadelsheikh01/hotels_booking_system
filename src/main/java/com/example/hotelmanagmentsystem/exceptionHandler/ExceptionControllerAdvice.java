package com.example.hotelmanagmentsystem.exceptionHandler;


import com.example.hotelmanagmentsystem.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundException notFoundException)
    {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage(), LocalDateTime.now()) ;
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorResponse> alreadyExist(AlreadyExistException alreadyExistException)
    {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.CONFLICT.value(), alreadyExistException.getMessage(), LocalDateTime.now()) ;
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
}
