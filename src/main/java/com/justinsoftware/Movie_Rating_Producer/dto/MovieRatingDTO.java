package com.justinsoftware.Movie_Rating_Producer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieRatingDTO(
        @NotBlank
        String movieName,
        @Min(value = 0, message = "Rating must be greater than 0")
        @Max(value = 5, message = "Rating must be less than 5")
        @NotNull
        Integer rating,
        @NotBlank
        String message
) {
}