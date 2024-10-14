package com.justinsoftware.Movie_Rating_Producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justinsoftware.Movie_Rating_Producer.dto.ErrorDTO;
import com.justinsoftware.Movie_Rating_Producer.dto.MovieRatingDTO;
import com.justinsoftware.Movie_Rating_Producer.dto.ResponseDTO;
import com.justinsoftware.Movie_Rating_Producer.event.Messaging;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/movie/rating")
public class MovieRatingController {

    private final Messaging messaging;
    private final ObjectMapper objectMapper;

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createMovieRating(@Valid @RequestBody MovieRatingDTO movieRatingDTO) {
        try {
            messaging.sendMessage("movieRatingProducer-json-createMovieRating", objectMapper.writeValueAsString(movieRatingDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(movieRatingDTO);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return handleGenericException("Json Processing Error");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, "Validation Error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    private ResponseEntity<ResponseDTO> handleGenericException(String errorMessage) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, new HashMap<>());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}
