package com.example.FakeStore.exceptions;

import com.example.FakeStore.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    private ResponseEntity<ExceptionDTO> handleArrayIndexOutOfBound(
            ArrayIndexOutOfBoundsException notFoundException
    ) {

        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
