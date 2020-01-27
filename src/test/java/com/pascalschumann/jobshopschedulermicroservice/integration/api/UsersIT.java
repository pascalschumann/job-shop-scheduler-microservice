package com.pascalschumann.jobshopschedulermicroservice.integration.api;

import static org.awaitility.Awaitility.await;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pascalschumann.jobshopschedulermicroservice.api.configuration.Constants;
import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;
import com.pascalschumann.jobshopschedulermicroservice.integration.AbstractIT;
import com.pascalschumann.jobshopschedulermicroservice.integration.utils.EntityFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Tests the /users endpoint
 */
public class UsersIT extends AbstractIT {

    @AfterEach
    public void after() {

        // cleanup created entities
        final User[] users = readUsers();
        for (final User user : users) {
            deleteUser(user.getId());
        }
    }

    /**
     * Includes testReadOne()
     */
    @Test
    public void testCreateUser() {
        final User expectedUser = EntityFactory.createUser();
        final User createdUser = createUser(expectedUser);
        await().until(() -> readUser(createdUser.getId()) != null);
        final User actualUser = readUser(createdUser.getId());

        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
    }

    @Test
    public void testReadAll() {
        final User expectedUser = EntityFactory.createUser();
        final User createdUser = createUser(expectedUser);
        final User[] actualUsers = readUsers();
        Assertions.assertArrayEquals(new User[] {createdUser}, actualUsers);
    }

    @Test
    public void testDeleteOne() {
        final User expectedUser = EntityFactory.createUser();
        final User createdUser = createUser(expectedUser);
        deleteUser(createdUser.getId());
        RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_USERS + "/" + createdUser.getId()).then()
                        .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testReplaceOne() {
        final User templateUser = EntityFactory.createUser();
        final User createdUser = createUser(templateUser);
        final User expectedUser = EntityFactory.createUser();
        expectedUser.setId(createdUser.getId());
        final User replacedUser = replaceUser(expectedUser);
        final User actualUser = readUser(expectedUser.getId());

        Assertions.assertEquals(expectedUser.getName(), replacedUser.getName());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
    }

    // ------------------- utils methods --------------

    private User readUser(final Long id) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_USERS + "/" + id).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(User.class);
    }

    private void deleteUser(final Long id) {
        RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .delete(Constants.API_ENDPOINT_USERS + "/" + id).then()
                        .statusCode(HttpStatus.SC_OK);
    }

    private User[] readUsers() {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_USERS).then().statusCode(HttpStatus.SC_OK)
                        .extract().as(User[].class);
    }

    private User replaceUser(final User userToCreate) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).contentType(ContentType.JSON)
                        .body(userToCreate).when().post(Constants.API_ENDPOINT_USERS).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(User.class);
    }

    private User createUser(final User userToReplace) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).contentType(ContentType.JSON)
                        .body(userToReplace).when().post(Constants.API_ENDPOINT_USERS).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(User.class);
    }
}
