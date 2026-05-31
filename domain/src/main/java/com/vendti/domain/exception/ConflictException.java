package com.vendti.domain.exception;

import lombok.Getter;

@Getter
public class ConflictException  extends Exception{

    private final String message;
    private final String error;

    public ConflictException() {
        this.message ="vendti.api.error.conflictException";
        this.error = "Conflict Error";
    }

    public ConflictException(String message) {
        this.message = message;
        this.error = "vendti.api.error.conflictException";
    }

}
