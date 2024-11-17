# Movie-Rating-Producer

Starter to a microservices EDA (Event Driven Architecture) system based on rating movies.

## Summary

This project as a whole contains two microservice where one service
[__Movie-Rating-Producer__](https://github.com/JustinStephenson/Movie-Rating-Producer)
transfers data to the other
[__Movie-Rating-Consumer__](https://github.com/JustinStephenson/Movie-Rating-Consumer) via
__Apache Kafka__ as the message broker. This allows the microservices to be independent of each other which can provide
a high degree of fault tolerance, performance, and scalability.

This is the software architecture paradigm Event Driven Architecture. It would be easy to add to this architecture to
extend business functionality or have other services consume the Producer data.

## Getting Started

Before running the project you will need to have a Docker and Docker Compose on your local machine.
One way to achieve this is to download and install Docker Desktop: https://www.docker.com/products/docker-desktop/

Follow the below instructions in order to get the application running:

1. Run the `docker/docker-compose.yml` file, to spin up all necessary docker containers
2. Start up this [__Movie-Rating-Producer__](https://github.com/JustinStephenson/Movie-Rating-Producer) microservice, as
   you would any Spring Application
3. Start up the [__Movie-Rating-Consumer__](https://github.com/JustinStephenson/Movie-Rating-Consumer) microservice
4. Go to the [Swagger Page](http://localhost:8080/swagger-ui/index.html) for the __Movie-Rating-Producer__ microservice,
   and create some movie data using a PUT request.
5. Query the Postgres database connected (localhost:5432), and view your data saved via the __Movie-Rating-Consumer__
   microservice.

## Testing

These microservices have the standard unit and integration tests.
The Integration tests are especially useful since they test the integration with __Apache Kafka__ by utilizing Test
Containers.