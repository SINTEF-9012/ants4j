# syntax=docker/dockerfile:1

FROM maven:3.5.4-jdk-8 as maven

# copy your source tree
COPY ./ ./
# RUN mvn dependency:go-offline -B
RUN mvn package

# FROM openjdk:16-alpine3.13

WORKDIR ./target

# set the startup command to run your binary
CMD ["java", "-jar", "ants4j-0.0.1-SNAPSHOT.jar"]