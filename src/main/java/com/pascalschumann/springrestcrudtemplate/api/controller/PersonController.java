package com.pascalschumann.springrestcrudtemplate.api.controller;

import static com.pascalschumann.springrestcrudtemplate.api.configuration.Constants.API_ENDPOINT_PERSONS;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pascalschumann.springrestcrudtemplate.api.repository.Person;
import com.pascalschumann.springrestcrudtemplate.api.repository.PersonRepository;

import io.swagger.annotations.Api;

/**
 * CRUD endpoint
 */
@RestController
@Api(value = "Persons", description = "is an example CRUD endpoint.", tags = {"Persons"})
public class PersonController {

    private final PersonRepository repository;

    public PersonController(final PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping(API_ENDPOINT_PERSONS)
    public List<Person> all() {
        return repository.findAll();
    }

    @PostMapping(API_ENDPOINT_PERSONS)
    public Person createPerson(@RequestBody final Person newPerson) {
        return repository.save(newPerson);
    }

    // Single item

    @GetMapping(API_ENDPOINT_PERSONS + "/{id}")
    public Person one(@PathVariable final Long id, final HttpServletResponse response) {

        final Optional<Person> foundPerson = repository.findById(id);
        if (foundPerson.isPresent()) {
            return foundPerson.get();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    @PutMapping(API_ENDPOINT_PERSONS)
    public Person replacePerson(@RequestBody final Person newPerson) {

        repository.findById(newPerson.getId())
                        .orElseThrow(() -> new RuntimeException("Could not found given entity."));
        return repository.save(newPerson);
    }

    @DeleteMapping(API_ENDPOINT_PERSONS + "/{id}")
    public void deletePerson(@PathVariable final Long id) {
        repository.deleteById(id);
    }
}
