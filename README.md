Web service providing New York City restaurant inspection health transactional and lookup data 
===========================

Provides an easy-to-use REST API to query for restaurant inspection data provided by 'The New York Department of Health and Mental Hygiene '
Original data set retrieved from: https://nycopendata.socrata.com/Health/Restaurant-Inspection-Results/4vkw-7nck



Application stack consists of:

- Dropwizard (Jersey, Jackson, Guava Jetty, Maven, Joda Datetime) for web service development
- MongoDB for the persistence layer
- Ruby for scripting, importing csv data into Mongo
- Swagger for API documentation
- Google Truth assertion / proposition framework for testing
- Mockito framework for mocking/verification

To run the application, checkout the code and run the followng commands from the project's root directory:

1) to build and package: mvn package 
2) to bring up the server: java -jar target/restaurant-health-0.0.1-SNAPSHOT.jar server restaurant-health.yml
