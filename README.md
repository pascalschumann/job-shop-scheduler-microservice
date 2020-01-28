# Job Shop Scheduler Microservice ![status](https://travis-ci.com/pascalschumann/job-shop-scheduler-microservice.svg?branch=master "Status")
Offers a job shop scheduler as microservice with authentication. The implementation of the scheduler itself is based on my [diploma thesis](https://github.com/pascalschumann/diploma-thesis-central-production-planning)

## Implemented features
- REST api for creating/receiving schedules
- Swagger documentation
- In-memory database H2 where resulting schedules are hold
- [CI/CD with Travis CI](https://travis-ci.com/pascalschumann/job-shop-scheduler-microservice)
- About endpoint which provides data about the service (version, buildTime, artifactId, ...)
- Dev spring profile, which prefills the database
- Basic authentication with configurable admin password
- Integration tests for REST api with Maven-Failsafe and RestAssured 

## Planned features
- Forced Https
- Bean validation
- Replacing in-memory database H2 by [Postgres docker container](https://hub.docker.com/_/postgres)

## Known bugs
- Naming convention: Since the scheduler is ported from C#, there some naming issues e.g. methods start with capital letter.
- /schedules api is not working yet (graph issues)

## Build, run Tests & operate
- Build<br>
`mvn clean package`
- Run tests<br>
`mvn clean verify -DadminPassword=password`
- Start app<br>
`java -jar job-shop-scheduler-microservice.jar -DadminPassword=password`
- Start app with dev profile<br>
`java -jar job-shop-scheduler-microservice.jar -DadminPassword=password -Dspring.profiles.active=dev`
- For requests use basic auth with user "admin" with configured -DadminPassword

## Used resources

- Starting point: [Spring initializer](https://start.spring.io/)
- For REST api: [Spring tutorial](https://spring.io/guides/tutorials/rest/) until "What makes something RESTful?" 
- For basic auth: [Spring security](https://docs.spring.io/spring-security/site/docs/5.2.2.BUILD-SNAPSHOT/reference/htmlsingle/#prerequisites)
- [RestAssured doc](https://github.com/rest-assured/rest-assured/wiki/Usage)
- [Maven-Failsafe doc](https://maven.apache.org/surefire/maven-failsafe-plugin/examples/inclusion-exclusion.html)
