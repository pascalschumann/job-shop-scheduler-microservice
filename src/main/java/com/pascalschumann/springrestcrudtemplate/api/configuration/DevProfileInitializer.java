package com.pascalschumann.springrestcrudtemplate.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pascalschumann.springrestcrudtemplate.api.repository.Person;
import com.pascalschumann.springrestcrudtemplate.api.repository.PersonRepository;

/**
 * Initializes the spring dev profile
 */
@Component
@Profile(Constants.SPRING_PROFILE_DEV)
public class DevProfileInitializer implements ApplicationRunner {

    private final Logger log = LoggerFactory.getLogger(DevProfileInitializer.class);


    @Override
    public void run(final ApplicationArguments args) throws Exception {

        // fill, if something has to be done after spring start
    }


    @Bean
    CommandLineRunner initDatabase(final PersonRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Person("Max Mustermann")));
            log.info("Preloading " + repository.save(new Person("Erika Mustermann")));
        };
    }
}
