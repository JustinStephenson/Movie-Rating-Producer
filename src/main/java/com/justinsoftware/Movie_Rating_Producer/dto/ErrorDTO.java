package com.justinsoftware.Movie_Rating_Producer.dto;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorDTO implements ResponseDTO {

    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final Map<String, String> errors;

    public ErrorDTO(HttpStatus httpStatus, String message, Map<String, String> errors) {
        this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.errors = new HashMap<>(errors);
    }
}
