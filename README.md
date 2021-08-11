# calbania-backend
Server created with Kotlin, Javalin Framework, Redis, Docker for raspberry systems/embedded systems with linux support for IoT reasons 

## Technologies
- Compiled by Java 8
- Developed using Kotlin and Javalin's Framework (https://javalin.io/)
- Docker and docker-compose
- Redis
- Java-jwt library (https://github.com/auth0/java-jwt)
- Gradle

## Support
This server has support any hardware that support linux as operational system. In this project, I created with focus to run on Raspberry Pi Zero W. 
I recommend that you develop on a desktop machine and deploy it on a rasperry.

## Requirements
- Any linux distro on your machine and raspberry (or any device that supports linux)
- Docker and docker-compose installed on your machine (https://docs.docker.com/engine/install/debian/ for raspberry and https://docs.docker.com/engine/install/ubuntu/ for ubuntu)
- Java 8 and Kotlin on your machine

## Recommendations
- Use IntelliJ as IDE for development

## How to build the code to embedded *.jar
  ```gradle clean build```
  
## How to run on your machine or raspberry
  ```docker-compose -f stack.yml up --build```
