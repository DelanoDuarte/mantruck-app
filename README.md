# Truck App
### Project with the objective to maintain truck data.

## Demo
![alt](demo/shorten_url_app.gif)

## Stack
### Backend
- Java 8
- Spring Boot - 2.2.5.RELEASE
  
### Frontend
- Angular - 9.0.2
  
### Database
- H2
  
### Test
- Junit - 4.13 **(Backend)** 
- Jasmine - 3.5.0 **(Frontend)**
- Karma - 4.3.0 **(Frontend)**

## Setup
### Client
Navigate to the client folder and using **npm** install all dependencies using the following command:

`npm install`

After downloading all dependencies, use the following command to run the **frontend**:

`ng serve`

### Server
Navigate to the server folder and using **npm** install all dependencies using the following command:

`mvn install`

After downloading all dependencies, use the following command to run the **backend**:

`mvn spring-boot:run`

To check the rest api docs, use the link below:

[API Documentation](http://localhost:8080/swagger-ui/index.html)

### Run tests
From the **server** folder, perform the following command to run unit tests:

`npm test`