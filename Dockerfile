FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/education-0.0.1-SNAPSHOT.jar education-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/education-0.0.1-SNAPSHOT.jar"]