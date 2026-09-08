package com.example.hotelmanagmentsystem.exceptionHandler;


import com.example.hotelmanagmentsystem.dto.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

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
    @ExceptionHandler(com.example.hotelmanagmentsystem.exceptionHandler.BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequest(BadRequestException badRequestException)
    {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage(), LocalDateTime.now()) ;
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> BusinessException(BusinessException businessException)
    {
        ErrorResponse errorResponse =
                new ErrorResponse(HttpStatus.CONFLICT.value(), businessException.getMessage(), LocalDateTime.now()) ;
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity TokenExpiredException(TokenExpiredException tokenExpiredException)
    {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), tokenExpiredException.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
    }
}
