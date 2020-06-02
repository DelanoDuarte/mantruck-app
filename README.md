# Truck App
### Project with the objective to maintain truck data.

## Demo
![alt](demo/man-truck-app_2.gif)

## Stack
### Server
- Java 8
- Spring Boot - 2.2.5.RELEASE
  
### Client
- Angular - 9.0.2
  
### Database
- H2
  
### Test
- Junit - 4.13 **(Server)** 
- Jasmine - 3.5.0 **(Client)**
- Karma - 4.3.0 **(Clint)**

## Setup
### Client
Navigate to the **client** folder and using **npm** install all dependencies using the following command:

`npm install`

After downloading all dependencies, use the following command to run the **client**:

`ng serve`

### Server
Navigate to the server folder and using **mvn** install all dependencies using the following command:

`mvn install`

After downloading all dependencies, navigate to **server/mantruck** and use the following command to run the **server**:

`mvn spring-boot:run`

To check the rest api docs, use the link below:

[API Documentation](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)

### Run tests
From the **server** folder, perform the following command to run unit tests:

`mvn test`

From the **client** folder, perform the following command to run unit tests:

`npm test`