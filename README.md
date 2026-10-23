# Spring Boot Backend Example

A Java backend example that combines a REST API with several common
infrastructure integrations. It includes registration and login endpoints, user
persistence, post creation through Kafka, relational data access with JPA,
MongoDB-backed post models, and Redis cache configuration.

## Stack

Java 17, Spring Boot 3.2, Maven, PostgreSQL, Spring Data JPA, MongoDB, Redis,
and Kafka.

## Requirements

Run PostgreSQL, MongoDB, Redis, and Kafka locally. The default service addresses
in `application.properties` are PostgreSQL on localhost:5432, MongoDB on
localhost:27017, Redis on localhost:6379, and Kafka on localhost:9092. The
application configures Kafka topics named `posts` and `examples`.

Create a PostgreSQL database named `springboot-example`, then provide
credentials through the environment:

~~~sh
export DB_USERNAME=postgres
export DB_PASSWORD=your-password
~~~

## Run

From the repository root:

~~~sh
./mvnw spring-boot:run
~~~

On Windows, use `mvnw.cmd spring-boot:run`. The Spring Boot web server uses its
default port, 8080, unless configured otherwise. The project also includes tests
under the Maven test source set; run them with `./mvnw test`.
