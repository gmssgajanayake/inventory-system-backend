package com.residuesolution.inventory_system_backend.advicer;


import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<StanderResponse> handelNotFoundExceptions(NoHandlerFoundException exception){
        return new ResponseEntity<>(
                new StanderResponse(
                        404,
                        "API endpoint not found",
                        exception.getRequestURL()
                ), HttpStatus.NOT_FOUND
        );
    }







}
