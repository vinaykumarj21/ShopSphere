package com.example.ProductCategoryService.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
//Exception.class : Generalized exception -> All the Exceptions will be Handled
    @ExceptionHandler({Exception.class})
    private ResponseEntity<String> handleException(){
        return new ResponseEntity<String>("Error has Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
