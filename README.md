# Astronomical Data REST API

This project interfaces with a public API to serve up astronomical data about bodies in our solar system. This project has two functions:

1. List the IDs of all astronomical bodies in the database.
1. List the details of any given astronomical body.

This project is a Spring Boot REST API that runs locally under an embedded Tomcat Web application server. Without any modifications, the base URI to reach the running application is: http://localhost:8080.

## About the project

This is a Maven project. To build it, first clone it to pull a copy from GitHub onto your local machine. Then, import the Maven project into your IDE.

## List all body IDs

To list all astronomical body IDs, start the application and send an HTTP GET request to http://localhost:8080/astronomical/data/body. Here's what happens:

1. The controller method BodyController.retrieveAllBodies() picks up the request.
1. The service method is called to retrieve all the body names: BodyService.retrieveBodyNames().
1. The service calls BodyDao.retrieveAllBodies(). The DAO method contacts the public REST API at [https://api.le-systeme-solaire.net/rest/bodies](https://api.le-systeme-solaire.net/rest/bodies) to retrieve JSON with detailed information on each astronomical body.
1. The service then extracts the body ID from each detail record, sorts them, and returns the list of IDs to the controller.
1. The controller then returns the list of IDs to the caller as a JSON array (list).

## List details for a body

To list the details for a single astronomical body, start the application and sent an HTTP GET request to http://localhost:8080/astronomical/data/body/{ID} where {ID} is the ID of an astronomical body (like "jupiter"). Here's what happens:

1. The controller method BodyController.retrieveBodyById() picks up the request.
1. The service method BodyService.retrieveBodyById() is called, passing the ID as a parameter.
1. The service calls BodyDao.retrieveBodyById(), passing the ID as a parameter.
1. The DAO method contacts the public REST API at [https://api.le-systeme-solaire.net/rest/bodies/{ID}](https://api.le-systeme-solaire.net/rest/bodies/{ID}) where {ID} is the ID of the body to retrieve. This method returns an Optional of type Body.
1. Back in the service method, if the Optional contains a Body object, it is returned to the controller. If the Optional is empty, a NoSuchElementException is thrown. When the unchecked exception is thrown, Spring returns a 500 (Internal Server Error) status. This is incorrect, as a not-found condition should result in a 404 (Not Found) status. To correct this, the NoSuchElementException is picked up by a global error handler. The error handler returns an error message and sets the status to 404 (Not Found). The error handler GlobalErrorHandler.java is found in the astronomical.data.controller.error package.

## Converting from JSON to Java and vice versa

Spring uses the Jackson JSON library. To convert back and forth from JSON to Java, instance variables in Java classes need to match the JSON key names exactly. These Java classes are found in the astronomical.data.controller.model package.

## Application configuration

Application configuration is in src/main/resources/application.yaml.

## Running the application

To run the application, run AstronomicalData.java as a Java application.