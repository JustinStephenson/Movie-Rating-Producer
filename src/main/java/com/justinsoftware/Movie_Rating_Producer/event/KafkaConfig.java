package com.justinsoftware.Movie_Rating_Producer.event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String CREATE_MOVIE_TOPIC = "movieRatingProducer-json-createMovieRating";
    public static final String UPDATE_MOVIE_TOPIC = "movieRatingProducer-json-updateMovieRating";

    @Bean
    protected NewTopic createMovieRatingTopic() {
        return TopicBuilder.name(CREATE_MOVIE_TOPIC)
                .build();
    }

    @Bean
    protected NewTopic updateMovieRatingTopic() {
        return TopicBuilder.name(UPDATE_MOVIE_TOPIC)
                .build();
    }
}
