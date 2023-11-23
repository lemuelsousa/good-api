# An Auth JWT API with Spring Boot 3.0 Security

This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Customized access denied handling

## Technologies
* [Gradle](https://gradle.org)
* [Spring Boot 3](https://spring.io)
* [Spring Security 3](https://spring.io/projects/spring-security)
* [PostgreSQL](https://www.postgresql.org)
* [Docker](https://docs.docker.com/get-started/)
* [JWT](https://jwt.io)
* [BCrypt](https://docs.spring.io/spring-security/reference/features/integrations/cryptography.html)


## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Docker
* docker compose

**To build and run the project, follow these steps:**

1. Clone the repository: `git clone https://github.com/lemuel-sousa/good-api.git`
2. Navigate to the project directory: `cd good-api`
3. Database 
   - Up postgres service with docker compose: `cd docker` after `docker-compose up -d`
   - Or add database "good_api" to postgres
4. Build the project: `./gradlew clean bootjar`
5. Run the project: `java -jar app/build/libs/app.jar`

-> The application will be available at http://localhost:8000.