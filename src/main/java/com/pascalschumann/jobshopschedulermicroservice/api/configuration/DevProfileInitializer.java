package com.pascalschumann.jobshopschedulermicroservice.api.configuration;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;
import com.pascalschumann.jobshopschedulermicroservice.api.repository.UserRepository;

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
    CommandLineRunner initDatabase(final UserRepository repository) {
        return args -> {
            final int maxLength = 10;
            for (int i = 0; i < maxLength; i++) {
                final String name = RandomStringUtils.randomAlphabetic(maxLength);
                final String password = RandomStringUtils.randomAlphabetic(maxLength);
                log.info("Filling db " + repository.save(new User(name, password)));
            }

        };
    }
}
