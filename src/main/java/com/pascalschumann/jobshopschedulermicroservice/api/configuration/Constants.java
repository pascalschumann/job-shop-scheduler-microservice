package com.pascalschumann.jobshopschedulermicroservice.api.configuration;

/**
 * Holds constants used by the service
 */
public class Constants {

    public static final String BASE_PATH = "/v1";
    public static final String API_ENDPOINT_USERS = BASE_PATH + "/users";
    public static final String API_ENDPOINT_SCHEDULES = "/schedules";
    public static final String API_ENDPOINT_ABOUT = BASE_PATH + "/about";
    public static final String SPRING_PROFILE_DEV = "dev";
    public static final String ADMIN_NAME = "admin";
    public static final String SYSTEM_PROPERTY_ADMIN_PASSWORD = "adminPassword";
}
