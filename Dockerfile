FROM openjdk:8-jdk-alpine
LABEL maintainer="Aneesh"
VOLUME /main-app
ADD target/tedtalk-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]