package com.justinsoftware.Movie_Rating_Producer.event;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createMovieRatingTopic() {
        return TopicBuilder.name("movieRatingProducer-json-createMovieRating")
                .build();
    }
}
