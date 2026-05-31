package com.vendti.infrastructure.api.exception;

import lombok.Getter;
import org.apache.coyote.BadRequestException;

@Getter
public class MethodArgumentNotValidException extends BadRequestException  {

    private final String error;
    private final String message;

    public MethodArgumentNotValidException(String error, String message) {
        this.error = error;
        this.message = message;
    }

}
