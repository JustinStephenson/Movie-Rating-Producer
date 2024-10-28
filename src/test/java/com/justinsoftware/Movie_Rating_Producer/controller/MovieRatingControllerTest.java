package com.justinsoftware.Movie_Rating_Producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justinsoftware.Movie_Rating_Producer.dto.MovieRatingDTO;
import com.justinsoftware.Movie_Rating_Producer.util.BaseTestContainer;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class MovieRatingControllerTest extends BaseTestContainer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private String baseUrl;
    private MovieRatingDTO baseMovieRatingDTO;

    @BeforeEach
    void setUp() {
        baseUrl = "/api/v1/movie/rating/";
        baseMovieRatingDTO = new MovieRatingDTO(123L, "Test Movie", 3, "Great movie");
    }

    @Test
    void testCreateMovieRating() throws Exception {
        // When
        ResultActions response = mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(baseMovieRatingDTO)));

        // Then
        response.andExpect(status().isCreated());
    }

    @Test
    void testUpdateMovieRating() throws Exception {
        // When
        ResultActions response = mockMvc.perform(put(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(baseMovieRatingDTO)));

        // Then
        response.andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource("createMovieRatingValidationErrorArguments")
    void createMovieRatingValidationError(Long userId, String movieName, Integer rating, String message) throws Exception {
        // Given
        MovieRatingDTO movieRatingDTO = new MovieRatingDTO(userId, movieName, rating, message);

        // When
        ResultActions response = mockMvc.perform(post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieRatingDTO)));

        // Then
        response.andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> createMovieRatingValidationErrorArguments() {
        return Stream.of(
                Arguments.of(null, "Test Movie", 4, "Movie was good!"),
                Arguments.of(123L, "", 4, "Movie was good!"),
                Arguments.of(123L, null, 4, "Movie was good!"),
                Arguments.of(123L, "Test Movie", -1, "Movie was good!"),
                Arguments.of(123L, "Test Movie", 6, "Movie was good!"),
                Arguments.of(123L, "Test Movie", null, "Movie was good!"),
                Arguments.of(123L, "Test Movie", 4, ""),
                Arguments.of(123L, "Test Movie", 4, null)
        );
    }
}