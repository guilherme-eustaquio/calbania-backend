FROM openjdk:11-jdk-slim
MAINTAINER guilherme.eustaquio.moreira@gmail.com
VOLUME /tmp
ARG JAR_FILE=./application/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]