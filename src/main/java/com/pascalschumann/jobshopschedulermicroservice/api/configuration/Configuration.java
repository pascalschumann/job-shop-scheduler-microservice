package com.pascalschumann.jobshopschedulermicroservice.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;

/**
 * Common beans used by the service
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${" + Constants.SYSTEM_PROPERTY_ADMIN_PASSWORD + "}")
    private String adminPassword;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    User userAdmin() {
        return new User(Constants.ADMIN_NAME, adminPassword);
    }
}
