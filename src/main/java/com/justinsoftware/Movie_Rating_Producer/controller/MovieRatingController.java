package com.justinsoftware.Movie_Rating_Producer.controller;

import com.justinsoftware.Movie_Rating_Producer.dto.ErrorDTO;
import com.justinsoftware.Movie_Rating_Producer.dto.MovieRatingDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/movie/rating")
public class MovieRatingController {

    @PostMapping("/")
    public ResponseEntity<MovieRatingDTO> createMovieRating(@Valid @RequestBody MovieRatingDTO movieRatingDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieRatingDTO);
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
}
