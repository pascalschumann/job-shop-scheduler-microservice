package com.pascalschumann.jobshopschedulermicroservice.integration.api;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pascalschumann.jobshopschedulermicroservice.api.configuration.Constants;
import com.pascalschumann.jobshopschedulermicroservice.api.model.About;
import com.pascalschumann.jobshopschedulermicroservice.integration.AbstractIT;

import io.restassured.RestAssured;

/**
 * Tests the /about endpoint
 */
public class AboutIT extends AbstractIT {

    @Test
    public void testReadAbout() {
        final About about = readAbout();
        Assertions.assertNotNull(about);
        Assertions.assertNotNull(about.getArtifactGroup());
        Assertions.assertNotNull(about.getArtifactId());
        Assertions.assertNotNull(about.getBuildTime());
        Assertions.assertNotNull(about.getJavaVersion());
        Assertions.assertNotNull(about.getServiceName());
        Assertions.assertNotNull(about.getServiceVersion());
        Assertions.assertNotNull(about.getSpringProfiles());


    }

    private About readAbout() {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_ABOUT).then().statusCode(HttpStatus.SC_OK)
                        .extract().as(About.class);
    }
}
