package com.pascalschumann.jobshopschedulermicroservice.integration.utils;

import org.apache.commons.lang3.RandomStringUtils;

import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;

/**
 * Provides static methods to generate entities.
 */
public class EntityFactory {

    public static User createUser() {
        final int nameLength = 10;
        final String name = RandomStringUtils.randomAlphabetic(nameLength);
        final String password = RandomStringUtils.randomAlphabetic(nameLength);
        return new User(name, password);
    }
}
