# Docker multi-stage build

# 1. Building the App with Maven
FROM maven:3.6-jdk-11

ADD . /student-app
WORKDIR /student-app

# Just echo so we can see, if everything is there :)
RUN ls -l

# Run Maven build
RUN mvn clean install -DskipTests

RUN echo "done!"

# Just using the build artifact and then removing the build-container

FROM openjdk:11-jre-slim

MAINTAINER Deepak Vijayan

VOLUME /tmp

# Add Spring Boot app.jar to Container
COPY --from=0 "/student-app/student-api/target/student-api-0.0.1-SNAPSHOT.jar" app.jar

ENV JAVA_OPTS=""

EXPOSE 8080

# Fire up our Spring Boot app by default
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]