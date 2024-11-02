package com.justinsoftware.Movie_Rating_Producer.util;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public class BaseTestContainer {

    private static final String KAFKA_CONTAINER = "confluentinc/cp-kafka:latest";

    private static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer(DockerImageName.parse(KAFKA_CONTAINER));

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    static {
        Startables.deepStart(kafka).join();
    }

}
