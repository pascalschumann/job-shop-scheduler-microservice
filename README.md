# spring-rest-crud-template ![status](https://travis-ci.com/pascalschumann/spring-rest-crud-template.svg?branch=master "Status")
A spring CRUD template to accelerate setting up a microservice with authentication.

## Implemented features
- A CRUD REST api
- Swagger documentation
- In-memory database H2 where entities are hold
- [CI/CD with Travis CI](https://travis-ci.com/pascalschumann/spring-rest-crud-template)
- About endpoint which provides data about the service (version, buildTime, artifactId, ...)
- Dev spring profile, which prefills the database
- Basic authentication with configurable admin password
- Integration tests for REST api with Maven-Failsafe and RestAssured 

## Planned features
- Forced Https
- Replacing in-memory database H2 by [Postgres docker container](https://hub.docker.com/_/postgres)

## Known bugs
- 

## Build, run Tests & operate
- Build<br>
`mvn clean package`
- Run tests<br>
`mvn clean verify -DadminPassword=password`
- Start app<br>
`java -jar spring-rest-crud-template.jar -DadminPassword=password`
- Start app with dev profile<br>
`java -jar spring-rest-crud-template.jar -DadminPassword=password -Dspring.profiles.active=dev`
- For requests use basic auth with user "admin" with configured -DadminPassword

## Used resources

- Starting point: [Spring initializer](https://start.spring.io/)
- For REST api: [Spring tutorial](https://spring.io/guides/tutorials/rest/) until "What makes something RESTful?" 
- For basic auth: [Spring security](https://docs.spring.io/spring-security/site/docs/5.2.2.BUILD-SNAPSHOT/reference/htmlsingle/#prerequisites)
- [RestAssured doc](https://github.com/rest-assured/rest-assured/wiki/Usage)
- [Maven-Failsafe doc](https://maven.apache.org/surefire/maven-failsafe-plugin/examples/inclusion-exclusion.html)
