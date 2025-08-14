package com.residuesolution.inventory_system_backend.advicer;


import com.residuesolution.inventory_system_backend.util.StanderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StanderResponse> handleInternalServerError(RuntimeException exception) {
        /*
        return new ResponseEntity<>(
                new StanderResponse(
                        500,
                        "Internal server error",
                        exception
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );
        */


        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        StanderResponse.builder()
                                .statusCode(500)
                                .message("Internal server error")
                                .data(exception.getLocalizedMessage()
                                ).build()
                );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<StanderResponse> handelNotFoundExceptions(NoHandlerFoundException exception){
        /*return new ResponseEntity<>(
                new StanderResponse(
                        404,
                        "API endpoint not found",
                        exception.getRequestURL()
                ), HttpStatus.NOT_FOUND
        );*/

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        StanderResponse.builder()
                                .statusCode(404)
                                .message("API endpoint not found")
                                .data(exception.getRequestURL())
                                .build()
                );
    }










}
