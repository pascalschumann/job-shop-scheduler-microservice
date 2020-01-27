package com.pascalschumann.jobshopschedulermicroservice.integration.api;

import static org.awaitility.Awaitility.await;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pascalschumann.jobshopschedulermicroservice.api.configuration.Constants;
import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleRequest;
import com.pascalschumann.jobshopschedulermicroservice.api.model.ScheduleResponse;
import com.pascalschumann.jobshopschedulermicroservice.integration.AbstractIT;
import com.pascalschumann.jobshopschedulermicroservice.integration.utils.EntityFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Tests the /schedules endpoint
 */
public class ScheduleIT extends AbstractIT {

    /**
     * Includes testReadOne()
     */
    @Test
    public void testCreateSchedule() {
        final ScheduleRequest scheduleRequest = EntityFactory.createScheduleRequest();
        final ScheduleResponse createdScheduleResponse = createSchedule(scheduleRequest);
        await().until(() -> readSchedule(createdScheduleResponse.getId()) != null);
        final ScheduleResponse actualScheduleResponse =
                        readSchedule(createdScheduleResponse.getId());

        Assertions.assertNotNull(actualScheduleResponse);
        // TODO
    }

    @Test
    public void testReadAll() {
        final ScheduleRequest expectedScheduleResponse = EntityFactory.createScheduleRequest();
        final ScheduleResponse createdScheduleResponse = createSchedule(expectedScheduleResponse);
        final ScheduleResponse[] actualScheduleResponses = readSchedules();

        Assertions.assertArrayEquals(new ScheduleResponse[] {createdScheduleResponse},
                        actualScheduleResponses);
    }

    private ScheduleResponse readSchedule(final Long id) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_SCHEDULES + "/" + id).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(ScheduleResponse.class);
    }

    private ScheduleResponse[] readSchedules() {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_SCHEDULES).then().statusCode(HttpStatus.SC_OK)
                        .extract().as(ScheduleResponse[].class);
    }

    private ScheduleResponse createSchedule(final ScheduleRequest schedule) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).contentType(ContentType.JSON)
                        .body(schedule).when().post(Constants.API_ENDPOINT_SCHEDULES).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(ScheduleResponse.class);
    }
}
