package com.pascalschumann.springrestcrudtemplate.integration.api;

import static org.awaitility.Awaitility.await;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.pascalschumann.springrestcrudtemplate.api.configuration.Constants;
import com.pascalschumann.springrestcrudtemplate.api.repository.Person;
import com.pascalschumann.springrestcrudtemplate.integration.AbstractIT;
import com.pascalschumann.springrestcrudtemplate.integration.utils.EntityFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

/**
 * Tests the /persons endpoint
 */
public class PersonsIT extends AbstractIT {

    @AfterEach
    public void after() {

        // cleanup created entities
        final Person[] persons = readPersons();
        for (final Person person : persons) {
            deletePerson(person.getId());
        }
    }

    /**
     * Includes testReadOne()
     */
    @Test
    public void testCreatePerson() {
        final Person expectedPerson = EntityFactory.createPerson();
        final Person createdPerson = createPerson(expectedPerson);
        await().until(() -> readPerson(createdPerson.getId()) != null);
        final Person actualPerson = readPerson(createdPerson.getId());

        Assertions.assertEquals(expectedPerson.getName(), actualPerson.getName());
    }

    @Test
    public void testReadAll() {
        final Person expectedPerson = EntityFactory.createPerson();
        final Person createdPerson = createPerson(expectedPerson);
        final Person[] actualPersons = readPersons();
        Assertions.assertArrayEquals(new Person[] {createdPerson}, actualPersons);
    }

    @Test
    public void testDeleteOne() {
        final Person expectedPerson = EntityFactory.createPerson();
        final Person createdPerson = createPerson(expectedPerson);
        deletePerson(createdPerson.getId());
        RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_PERSONS + "/" + createdPerson.getId()).then()
                        .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testReplaceOne() {
        final Person templatePerson = EntityFactory.createPerson();
        final Person createdPerson = createPerson(templatePerson);
        final Person expectedPerson = EntityFactory.createPerson();
        expectedPerson.setId(createdPerson.getId());
        final Person replacedPerson = replacePerson(expectedPerson);
        final Person actualPerson = readPerson(expectedPerson.getId());

        Assertions.assertEquals(expectedPerson.getName(), replacedPerson.getName());
        Assertions.assertEquals(expectedPerson.getName(), actualPerson.getName());
    }

    // ------------------- utils methods --------------

    private Person readPerson(final Long id) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_PERSONS + "/" + id).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(Person.class);
    }

    private void deletePerson(final Long id) {
        RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .delete(Constants.API_ENDPOINT_PERSONS + "/" + id).then()
                        .statusCode(HttpStatus.SC_OK);
    }

    private Person[] readPersons() {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).when()
                        .get(Constants.API_ENDPOINT_PERSONS).then().statusCode(HttpStatus.SC_OK)
                        .extract().as(Person[].class);
    }

    private Person replacePerson(final Person personToCreate) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).contentType(ContentType.JSON)
                        .body(personToCreate).when().post(Constants.API_ENDPOINT_PERSONS).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(Person.class);
    }

    private Person createPerson(final Person personToReplace) {
        return RestAssured.given().spec(REQUEST_SPECIFICATION).contentType(ContentType.JSON)
                        .body(personToReplace).when().post(Constants.API_ENDPOINT_PERSONS).then()
                        .statusCode(HttpStatus.SC_OK).extract().as(Person.class);
    }
}
