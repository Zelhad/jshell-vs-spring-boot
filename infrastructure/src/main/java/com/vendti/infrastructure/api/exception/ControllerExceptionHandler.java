package com.vendti.infrastructure.api.exception;

import com.vendti.domain.exception.ConflictException;
import com.vendti.domain.exception.product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseError> productNotFound(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error("vendti.api.error.productNotFound")
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseError> conflict(ConflictException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.builder()
                .status(HttpStatus.CONFLICT)
                .error(e.getError())
                .message(e.getMessage())
                .build());
    }




}



