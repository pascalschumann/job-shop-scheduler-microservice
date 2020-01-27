package com.pascalschumann.springrestcrudtemplate.integration;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.pascalschumann.springrestcrudtemplate.Application;
import com.pascalschumann.springrestcrudtemplate.api.configuration.Constants;
import com.pascalschumann.springrestcrudtemplate.api.model.User;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;


/**
 * This class initializes the test environment.
 */
// @RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = Application.class)
public class AbstractIT {

    protected final String BASE_URL = "http://localhost:8080";

    private final User userAdmin = new User(Constants.ADMIN_NAME,
                    System.getProperty(Constants.SYSTEM_PROPERTY_ADMIN_PASSWORD));
    protected RequestSpecification REQUEST_SPECIFICATION;


    @BeforeEach
    public void before() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder().setBaseUri(BASE_URL)
                        .setAuth(RestAssured.basic(userAdmin.getUserName(),
                                        userAdmin.getPassword()))
                        .addFilter(new ResponseLoggingFilter())
                        .addFilter(new RequestLoggingFilter()).build();

    }
}
