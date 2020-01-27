package com.pascalschumann.springrestcrudtemplate.integration.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.pascalschumann.springrestcrudtemplate.api.repository.Person;

/**
 * Provides static methods to generate entities.
 */
public class EntityFactory {

    public static Person createPerson() {
        final int nameLength = 10;
        return new Person(RandomStringUtils.randomAlphabetic(nameLength));
    }
}
