package com.pascalschumann.jobshopschedulermicroservice.api.controller;

import static com.pascalschumann.jobshopschedulermicroservice.api.configuration.Constants.API_ENDPOINT_USERS;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pascalschumann.jobshopschedulermicroservice.api.repository.User;
import com.pascalschumann.jobshopschedulermicroservice.api.repository.UserRepository;

import io.swagger.annotations.Api;

/**
 * CRUD endpoint
 */
@RestController
@Api(value = "Users", description = "is an example CRUD endpoint.", tags = {"Users"})
public class UserController {

    private final UserRepository repository;

    public UserController(final UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(API_ENDPOINT_USERS)
    public List<User> all() {
        return repository.findAll();
    }

    @PostMapping(API_ENDPOINT_USERS)
    public User createUser(@RequestBody final User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping(API_ENDPOINT_USERS + "/{id}")
    public User one(@PathVariable final Long id, final HttpServletResponse response) {

        final Optional<User> foundUser = repository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    @PutMapping(API_ENDPOINT_USERS)
    public User replaceUser(@RequestBody final User newUser) {

        repository.findById(newUser.getId())
                        .orElseThrow(() -> new RuntimeException("Could not found given entity."));
        return repository.save(newUser);
    }

    @DeleteMapping(API_ENDPOINT_USERS + "/{id}")
    public void deleteUser(@PathVariable final Long id) {
        repository.deleteById(id);
    }
}
