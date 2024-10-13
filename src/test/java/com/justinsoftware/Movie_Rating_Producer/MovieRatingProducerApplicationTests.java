package com.justinsoftware.Movie_Rating_Producer;

import com.justinsoftware.Movie_Rating_Producer.controller.MovieRatingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MovieRatingProducerApplicationTests {

	@Autowired
	private MovieRatingController movieRatingController;

	@Test
	void contextLoads() {
		assertThat(movieRatingController).isNotNull();
	}

}
