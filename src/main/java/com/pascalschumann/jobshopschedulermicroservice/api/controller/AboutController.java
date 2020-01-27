package com.pascalschumann.jobshopschedulermicroservice.api.controller;

import static com.pascalschumann.jobshopschedulermicroservice.api.configuration.Constants.BASE_URL_ABOUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pascalschumann.jobshopschedulermicroservice.api.model.About;

import io.swagger.annotations.Api;

/**
 * Endpoint provides information about the service
 */
@RestController
@Api(value = "About", description = "the service", tags = {"About"})
public class AboutController {

    private final BuildProperties buildProperties;
    private final Environment environment;

    @Autowired
    public AboutController(final BuildProperties buildProperties, final Environment environment) {
        this.buildProperties = buildProperties;
        this.environment = environment;
    }

    @GetMapping(BASE_URL_ABOUT)
    About about() {

        final About about = new About();

        // artifact's name from pom
        about.setServiceName(buildProperties.getName());
        // artifact version
        about.setServiceVersion(buildProperties.getVersion());
        // dateTime of build
        about.setBuildTime(buildProperties.getTime().toString());
        about.setArtifactId(buildProperties.getArtifact());
        about.setArtifactGroup(buildProperties.getGroup());
        about.setJavaVersion(buildProperties.get("java.target"));
        about.setSpringProfiles(environment.getActiveProfiles());

        return about;
    }
}
