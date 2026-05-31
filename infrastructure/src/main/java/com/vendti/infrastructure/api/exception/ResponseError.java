package com.vendti.infrastructure.api.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ResponseError {
    @NonNull
    private HttpStatus status;
    @NonNull
    private String error;
    private List<String> info;
    @NonNull
    private String message;

}
