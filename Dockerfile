# syntax=docker/dockerfile:1

# FROM openkbs/jdk-mvn-py3

FROM maven:3.5.4-jdk-8 as maven
        
#RUN pip install --no-cache-dir --upgrade pip && \
#    pip install --no-cache-dir matplotlib 

RUN apt-get update \
   && apt-get install -y python3 \
   && apt-get install -y python3-pip

RUN pip3 install --no-cache-dir matplotlib numpy pandas tensorflow

# copy your source tree
COPY ./ ./
COPY ./src/main/resources/python /python
# RUN mvn dependency:go-offline -B
RUN mvn package

# FROM openjdk:16-alpine3.13

# set the startup command to run your binary
CMD ["java", "-jar", "target/ants4j-0.0.1-SNAPSHOT.jar"]