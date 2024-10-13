package com.justinsoftware.Movie_Rating_Producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justinsoftware.Movie_Rating_Producer.dto.MovieRatingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MovieRatingControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private MovieRatingDTO movieRatingDTO;

    @Test
    void createMovieRating() throws Exception {
        // Given
        movieRatingDTO = new MovieRatingDTO("Test Movie", 3, "Great movie");

        // When
        ResultActions response = mockMvc.perform(post("/api/v1/movie/rating/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieRatingDTO)));

        // Then
        response.andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource("createMovieRatingValidationErrorArguments")
    void createMovieRatingValidationError(String movieName, Integer rating, String message) throws Exception {
        // Given
        movieRatingDTO = new MovieRatingDTO(movieName, rating, message);

        // When
        ResultActions response = mockMvc.perform(post("/api/v1/movie/rating/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieRatingDTO)));

        // Then
        response.andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> createMovieRatingValidationErrorArguments() {
        return Stream.of(
                Arguments.of("", 4, "Movie was good!"),
                Arguments.of(null, 4, "Movie was good!"),
                Arguments.of("Test Movie", -1, "Movie was good!"),
                Arguments.of("Test Movie", 6, "Movie was good!"),
                Arguments.of("Test Movie", null, "Movie was good!"),
                Arguments.of("Test Movie", 4, ""),
                Arguments.of("Test Movie", 4, null)
        );
    }
}