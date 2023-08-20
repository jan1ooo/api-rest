package com.jan1ooo.apirest.controller;


import com.jan1ooo.apirest.exception.BodyBadRequestException;
import com.jan1ooo.apirest.exception.BodyUnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BodyUnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleUnprocessableEntityException(BodyUnprocessableEntityException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(BodyBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(BodyBadRequestException ex){
        return ex.getMessage();
    }
}
