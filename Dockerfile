FROM gradle:5.3.0-jdk8-alpine AS build
COPY --chown=gradle:gradle ./ /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM openjdk:8-alpine
RUN mkdir /app
COPY --from=build /home/gradle/src/application/build/libs/*.jar /app/calbania-application.jar
ENTRYPOINT ["java","-jar","/app/calbania-application.jar"]