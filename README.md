# Tedtalk Service

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app for Managing Ted talks.

## Description

This project contains several API's for Ted talk service application which includes save ted talks from csv, updation individual ted talks, find based on filters, deleting ted talk and etc .

## Documentation


   1.Postman collections and environment variables are available in the project folder
   
   2.Swagger documentation is available at http://localhost:8080/tedtalk-manager/swagger-ui.html#!/
   
				
## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [PostgreSQL](https://www.postgresql.org/)

## Technical features included

1. Global exception handler is implemented 
2. i18 is implemented with a message property file
3. Flyway is added for migration
4. Spring security with JWT and basic authentication and role based authorization is implemented.


## Running the application using Docker

1. checkout the project to the workspace 
2. Go to the project folder in command prompt
3. build the project by using the command 'mvn clean install'
4. Execute the following docker command

        
		docker-compose up --build
		


  

