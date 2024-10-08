package com.justinsoftware.Movie_Rating_Producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MovieRatingProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRatingProducerApplication.class, args);
	}

	@RequestMapping("/")
	public String helloWorld() {
		return "Hello World!";
	}
}
