# calbania-backend
Server created with Kotlin, Javalin Framework, Redis, Docker for Raspberry systems/embedded systems with Linux support for IoT reasons.

## Technologies
- Compiled with Java 8
- Developed using Kotlin and Javalin Framework (https://javalin.io/)
- Docker and docker-compose
- Redis
- Java-jwt library (https://github.com/auth0/java-jwt)
- Gradle

## Support
This server has support for any hardware that supports Linux as the operating system. In this project, it is created with a focus on running on Raspberry Pi Zero W. 
I recommend that you develop on a desktop machine and deploy it on a Raspberry Pi.

## Requirements
- Any Linux distribution on your machine and Raspberry Pi (or any device that supports Linux)
- Docker and docker-compose installed on your machine (https://docs.docker.com/engine/install/debian/ for Raspberry Pi and https://docs.docker.com/engine/install/ubuntu/ for Ubuntu)
- Java 8 and Kotlin on your machine

## Recommendations
- Use IntelliJ as an IDE for development

## How to build the code to an embedded *.jar
  ```gradle clean build```
  
## How to run on your machine or Raspberry Pi
  ```docker-compose -f stack.yml up --build```
